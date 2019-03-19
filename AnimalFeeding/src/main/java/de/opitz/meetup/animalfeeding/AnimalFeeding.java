package de.opitz.meetup.animalfeeding;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;

import java.util.Properties;
import java.util.logging.Logger;

public class AnimalFeeding {
    //TODO bonus exercise: Think about how to implement parallelism her (Hint: Have a look at the connector sink)

    private static final Logger LOGGER = Logger.getLogger(AnimalFeeding.class.getName());
    private String bootstrapServers = "127.0.0.1:9092"; //TODO initialize with your bootstrap servers
    private String inputTopic, outputTopic; //TODO initialize with your topics
    private String appIDConfig = "1"; //TODO initialize with your app id
    private KafkaStreams kafkaStreams;
    private Properties properties;

    /**
     * Initializes the properties of the StreamProcess
     * Reads from the kafkaShowcase.properties file
     */
    public AnimalFeeding() {
        initializeProperties();
        createStreamProcessor();
    }

    /**
     * Starts a StreamProcessor
     *
     * @param args
     */
    public static void main(String[] args) {
        AnimalFeeding animalFeeding = new AnimalFeeding();
        animalFeeding.startStreamProcessing();
    }

    /**
     * Initializes the properties for the Kafka Stream Processor
     */
    private void initializeProperties() {
        properties = new Properties();
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, appIDConfig);
        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StringSerializer.class.getName());
    }

    /**
     * Creates a new Kafka Stream Processor with the values given in the properties of the object
     * Takes an message from a inputTopic adds a message/String to the message and sends it to another outputTopic
     */
    private void createStreamProcessor() {
        //create a topology
        StreamsBuilder streamsBuilder = new StreamsBuilder();


        //TODO create your stream processor
            //TODO a) stream your topic
            //TODO b) map it (e.g. let your animals make some fancy stuff)
            //TODO c) specify the output topic

        Topology topology = streamsBuilder.build();

        kafkaStreams = new KafkaStreams(topology, properties);
    }

    /**
     * Starts the stream processing
     */
    public void startStreamProcessing() {
        //TODO start your kafka streams
    }
}