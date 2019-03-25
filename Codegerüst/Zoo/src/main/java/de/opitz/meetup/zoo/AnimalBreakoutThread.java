package de.opitz.meetup.zoo;

import de.opitz.meetup.Animals.Animal;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimalBreakoutThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalBreakoutThread.class.getName());
    private String topic;
    private KafkaProducer<String, String> kafkaProducer;
    private Callback callback;
    private String data;

    /**
     * Constructor with the data to be written to kafka
     *
     * @param animal           The Animal, to be send to kafka
     * @param producer         The KafkaProducer via which the Thread should sendK data to kafka
     * @param topic            the topic on which the producer should write
     */
    public AnimalBreakoutThread(Animal animal, KafkaProducer producer, String topic) {
        this.topic = topic;
        this.kafkaProducer = producer;

        callback = new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                //Executes every time a record is successfully sent or an exception is thrown
                if (e != null) {
                    //exception was thrown
                    LOGGER.warn("Error while producing data to kafka" + e.toString());
                }
            }
        };
    }


    /**
     * ...
     */
    public void run() {
        //TODO get Data from Animal
        //TODO send Data to Kafka
    }
}
