package de.opitz.meetup.zoo;

import de.opitz.meetup.Animals.Animal;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class Zoo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Zoo.class.getName());
    private String topic; //TODO initialize with your topic
    private String bootstrapServers; //TODO initialize with your bootstrapServers (hint: the port is per default 9092)
    private Properties kafkaProperties;
    private KafkaProducer kafkaProducer;

    public static void main(String[] argv) {
        LOGGER.info("starting Zoo with animals... ");

        Zoo zoo = new Zoo();
        zoo.animalOutbreak();
    }

    /**
     * @return An Object of Java.util.Properties filled with the properties for creating a KafkaProducer
     */
    private Properties readKafkaProperties() {
        Properties properties = new Properties();

        //create producer kafkaProperties
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //create saver producer
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "2147483647");

        //high throughput settings
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "23552");

        return properties;
    }

    /**
     * Creates new animals and delegates them to a threat which produces them to kafka them
     *
     * @throws IOException
     */
    private void animalOutbreak() {
        LOGGER.info("Zoo-Assistant: Oh shit! There was an animal breakout!");
        LOGGER.info("Zookeeper: We have to bring them back in the Database");
        LOGGER.info("Zoo-Assistant: They are all running to kafka, so it should be easy for us.");
        LOGGER.info("Zookeeper: That's good for you. Anyway, hurry up!");

        int counter = 0;
        //TODO Think about your kafka properties (hint: there may be a method already for you)
        kafkaProducer = null; //TODO create your kafka producer

        while (true) {
            Animal animal = null; //TODO initialize with a concrete Animal (hint: you may use the Factory)

            Thread producerThread = new AnimalBreakoutThread(animal, kafkaProducer, topic);
            producerThread.start();

            counter++;
            LOGGER.info("Animal counter: " + counter);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
