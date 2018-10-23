FROM java:8
MAINTAINER pradeep4jobs@gmail.com
VOLUME /tmp
RUN mkdir -p /usr/share/spark/data
ADD target/pim-spark-server-1.0-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'
ADD docker/wait-for-it.sh /wait-for-it.sh
RUN bash -c 'chmod 777 /wait-for-it.sh'
CMD exec /bin/sh -c "trap : TERM INT; while :; do sleep 2073600; done & wait"