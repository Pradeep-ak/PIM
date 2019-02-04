from kafka import KafkaConsumer, KafkaProducer
from kafka.errors import KafkaError
from JMS import config
from json import dumps

def sendMsg(msg='', topicName='default-topic'):
    producer = KafkaProducer(bootstrap_servers=['kafka:9092'])
    # Asynchronous by default
    future = producer.send(topicName, msg)
    # Block for 'synchronous' sends
    try:
        record_metadata = future.get(timeout=10)
    except KafkaError:
        # Decide what to do if produce request failed...
        #log.exception()
        pass
    # Successful result returns assigned partition and offset
    print(record_metadata.topic)
    print(record_metadata.partition)
    print(record_metadata.offset)
