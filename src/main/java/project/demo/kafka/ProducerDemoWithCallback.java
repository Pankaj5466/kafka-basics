package project.demo.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {
    private static final Logger log  = LoggerFactory.getLogger(ProducerDemoWithCallback.class.getSimpleName());

    public static void main(String[] args) {
//        System.out.println("Inside ProducerDemo class");

        log.info("Inside Producer Demo Class");

        //Step1: Create Producer Properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092"); // 172.29.221.135
//        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"0.0.0.0:9092"); // 172.29.221.135
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //Step2: Create kafka producer
        KafkaProducer<String,String > producer = new KafkaProducer<String, String>(properties); //create producer with properties set above



        // Send multiple records
        for(int i=0;i<10;i++) {
            //Step3: Create a producer record
            ProducerRecord<String , String> producerRecord =
                    new ProducerRecord<>("demo_java","hello world -> " + i);

            //Step4: send data in async way

            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e == null) {
                        log.info("Received new metadata/ \n" +
                                "Topic: " + metadata.topic() + "\n" +
                                "Partition: " + metadata.partition() + "\n" +
                                "Offset: " + metadata.offset() + "\n" +
                                "TimeStamp: " + metadata.timestamp() + "\n");
                    } else {
                        log.error("Error While sending data to kafka\n");
                    }
                }
            });
        }

        //flush data (synchronous) //wait to send data
        producer.flush();

        //flush and close in single operation
        producer.close();
    }
}
