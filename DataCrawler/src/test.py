#!/usr/bin/python -u

import urllib
import httplib2
import json
import csv
import concurrent.futures
from JMS import kafkaService
import traceback

def parse_product(productId):
    url = "https://browse-api.jcpenney.com/v2/product-aggregator/{}?regionId=highs".format(productId)
    headers = {'Accept': 'application/json', "Content-Type":"application/json" }

    serverContent = httplib2.Http(disable_ssl_certificate_validation=True).request(url,'GET',headers=headers,body=urllib.parse.urlencode({}))
    if serverContent[0].status==200:
        jsonObj = json.loads(serverContent[1])
        output={}
        for lot in jsonObj['lots']:
            output['productId'] = lot['id']
            output['name'] = jsonObj['name']
            output['description'] = lot['description']
            output['channelOffering'] = lot['channelOffering']
            output['canShipToStore'] = lot['canShipToStore']
            output['division'] = lot['organization']['division']
            output['subdivision'] = lot['organization']['subdivision']
            output['entity'] = lot['organization']['entity']
            output['maxQuantity'] = lot['maxQuantity']
            output['productType'] = lot['productType']
            output['itemType'] = lot['itemType']

            output['shipFromFactory'] = lot['shipping']['shipFromFactory']
            output['international'] = lot['shipping']['international']
            output['truckable'] = lot['shipping']['truckable']

            #Active Skus for the lots.
            activeskus = list()
            for sku in lot['items']:
                activeskus.append(sku['id'])
            output['activeskus'] = activeskus
            # Attributes for the lots.
            attrJson = dict()
            for attributes in lot['bulletedAttributes']:
                attributes = attributes['description']
                attr = attributes.split(":")
                attrJson[attr[0].strip()]=attr[1].strip()
            output['attributes'] = attrJson

            output['brand'] = jsonObj['brand']['name']
            output['bopisEligibility'] = jsonObj['bopisEligibility']
            output['enableMiniPDP'] = jsonObj['enableMiniPDP']
            output['type'] = jsonObj['type']
            # Parent Items.
            output['parent'] = jsonObj['id']
            output['category'] = jsonObj['category']['id']
            output['deparment'] = jsonObj['category']['parent']['id']
            #Images Primary and Alternate.
            alternate=list()
            for image in jsonObj['images']:
                if image['type'] == "PRIMARY":
                    output['primary'] = image['url']
                else:
                    alternate.append(image['url'])
            output['alternateImg'] = alternate
            #print(json.dumps(output, indent=4, sort_keys=True))
            #return json.dumps(output, ensure_ascii=False).encode('utf8')
            return output

if __name__ == "__main__":
    products =[]
    file_name = "/app/products_list.csv"
    with open(file_name) as csvfile:
        reader = csv.reader(csvfile)
        products = list(reader)
    # print(products)
    with open("/tmp/test.txt", "wb") as myfile:
        with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
            future_to_url = {executor.submit(parse_product, product[0]): product for product in products}
            for future in concurrent.futures.as_completed(future_to_url):
                stats = future_to_url[future]
                try:
                    data = future.result()
                    if data:
                        print(data)
                        #myfile.write(data)
                        kafkaService.sendMsg(json.dumps(data, ensure_ascii=False).encode('utf8'), 'product-feed-topic.t')

                except Exception as exc:
                    print('%r generated an exception: %s' % (stats, exc))
                    print(traceback.format_exc())
