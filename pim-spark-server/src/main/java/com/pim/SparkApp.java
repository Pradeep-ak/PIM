package com.pim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pim.kafka.KafkaConstants;
import com.pim.kafka.KafkaProducer;
import com.pim.model.SkuModel;

import scala.Tuple2;

/**
 * Created by pkulkar4 on 9/15/18.
 */
public class SparkApp {

    public static void main(String[] args) {

        SparkApp app = new SparkApp();
        if (args[0]!=null && args[0].equalsIgnoreCase("sku"))
            app.runSkuLoad();
        if (args[0]!=null && args[0].equalsIgnoreCase("product"))
            app.runProd();
        else
            System.out.println("No param/valid param passed.");
    }

    private void runProd() {

    }

    private void runSkuLoad(){
        SparkConf conf = new SparkConf().setAppName("PK").setMaster("local[*]");
        SparkContext sc = new SparkContext(conf);
        JavaRDD<String> fileRDD = sc.textFile("/usr/share/spark/data/sku/mainskudata.txt", 20).toJavaRDD();

        JavaPairRDD<String, String[]> mainSkuDataRDDD = fileRDD.mapToPair(s -> {
            String[] line = s.split(",");
            return new Tuple2<String, String[]>(line[0], line);
        });

        JavaRDD<String> skuAttrFileRDD = sc.textFile("/usr/share/spark/data/sku/mainskuAttdata.txt", 20).toJavaRDD();

        JavaPairRDD<String, Iterable<String[]>> attSkuDataRDDD = skuAttrFileRDD.mapToPair(s -> {
            String[] line = s.split(",");
            return new Tuple2<String, String[]>(line[0], line);
        }).groupByKey();

        JavaPairRDD<String, Map<String, String>> refinedAttSkuDataRDDD = attSkuDataRDDD.mapToPair(s -> {
            String key = s._1;
            Map<String, String> att = new HashMap<>();
            s._2.forEach(
                    d -> {
                        att.put(d[1], d[2]);
                    }
            );
            return new Tuple2<String, Map<String, String>>(key,att);
        });

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

        JavaRDD<SkuModel> skuModelRDD = mainSkuDataRDDD.join(refinedAttSkuDataRDDD).map(
                s ->
                        SkuModel.builder()
                                .id(s._1)
                                .displayName(s._2._1[1])
                                .description(s._2._1[2])
                                .creationDate(format.parse(s._2._1[3]))
                                .startDate(format.parse(s._2._1[4]))
                                .endDate(format.parse(s._2._1[5]))
                                .skuNum(s._2._1[6])
                                .swatchImageReference(s._2._1[7])
                                .status((Integer.parseInt(s._2._1[8])==0?false:true))
                                .hideDisplay((Integer.parseInt(s._2._1[9])==0?false:true))
                                .barcode(s._2._1[10])
                                .parentProducts(s._2._1[11])
                                .lastUpatedTime(new Date()).
                                type("sku")
                                .dynamicAttributes(s._2._2)
                                .lineAttributes(s._2._2)
                                .build()

        );
//        KafkaProducer kafkaProducer = new KafkaProducer(KafkaConstants.KAFKA_BROKER_STRING);
//        skuModelRDD.collect().forEach(e-> {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//            try {
//                String json = ow.writeValueAsString(e);
//                //System.out.println(json);
//                kafkaProducer.sendMessage(KafkaConstants.KAFKA_TOPIC, json);
//                System.out.println(e.getId());
//            } catch (JsonProcessingException e1) {
//                e1.printStackTrace();
//            }
//        });
        skuModelRDD.foreachPartition(e -> {
            KafkaProducer kafkaProducer = new KafkaProducer(KafkaConstants.KAFKA_BROKER_STRING);
            while (e.hasNext()){
                SkuModel skuModel = e.next();
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                try {
                    String json = ow.writeValueAsString(skuModel);
                    kafkaProducer.sendMessage(KafkaConstants.KAFKA_TOPIC, json);
                    System.out.println(skuModel.getId());

                } catch (JsonProcessingException e1) {
                    e1.printStackTrace();
                }
            }
            kafkaProducer.closeProducer();
        });
    }
}
