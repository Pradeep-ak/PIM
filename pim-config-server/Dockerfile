FROM java:8
MAINTAINER pradeep4jobs@gmail.com
VOLUME /tmp
ADD target/pim-config-server-1.0-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'
ADD docker/wait-for-it.sh /wait-for-it.sh
RUN bash -c 'chmod 777 /wait-for-it.sh'
ENTRYPOINT ["java", "-Xms256m", "-Xmx1024m" ,"-jar","/app.jar"]