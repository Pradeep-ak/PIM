FROM python:3.6-alpine
MAINTAINER pradeep4jobs@gmail.com
VOLUME /tmp
COPY requirements.txt /
RUN pip install -r /requirements.txt
RUN mkdir -p /app
#COPY src/ /app
#WORKDIR /app
CMD exec /bin/sh -c "trap : TERM INT; while :; do sleep 2073600; done & wait"