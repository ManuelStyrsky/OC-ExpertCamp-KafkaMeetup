package de.opitz.meetup.animalfetching;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Set;

public class AnimalFetchingThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalFetchingThread.class.getName());


    private static JsonParser jsonParser = new JsonParser();
    private Connection connection;
    private String bootstrapServers; //TODO initialize with your bootstrap servers
    private String topic; //TODO initialize with your topic
    private String groupIDConfig; //TODO give your app an identifier
    private String tablename; //TODO give your table name here
    private KafkaConsumer<String, String> consumer;

    private int threadIndex;

    /**
     * @param index the index of the Thread in a Thread array
     */
    public AnimalFetchingThread(int index) {
        this.threadIndex = index;
    }

    /**
     * initializes the properties, the kafka consumer and a connection to the database
     * starts a new kafka consumer
     */
    @Override
    public void run() {
        this.initializeConsumer();
        try {
            this.initializeDBConnection();
            this.consumeDataFromTopic();
        } catch (SQLException e) {
            LOGGER.error(threadIndex + "-Error while initializing database connection: " + e.getMessage());
        }
        LOGGER.warn(threadIndex + "-Consumer thread stopped");
    }

    /**
     * Extracts an SQL statement to write all the values in the jsonObject in the database with the corresponding fields
     *
     * @param jsonObject the jsonObject to be stored in the database
     * @return
     */
    private String extractSQLInsertFromJSON(JsonObject jsonObject) {
        String columnNames = "";
        String data = "";

        Set<String> keys = jsonObject.keySet();

        for (String elem : keys) {
            columnNames += elem + ", ";
            data += "'" + jsonObject.get(elem) + "', ";
        }

        columnNames = columnNames.substring(0, columnNames.length() - 2);
        data = data.substring(0, data.length() - 2);

        data = data.replaceAll("\"", "");

        String sql = "INSERT INTO " + tablename + " (" + columnNames + ") VALUES (" + data + ")";

        return sql;
    }

    /**
     * initializes a new kafka consumer and subscribes it to the output topic, specified in the .properties file
     */
    private void initializeConsumer() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupIDConfig);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //TODO create consumer

        //TODO subscribe consumer to topic(s)
    }

    /**
     * initializes a connection to the database with the properties given in the .properties file
     */
    private void initializeDBConnection() throws SQLException {

        //TODO give here the credentials to your Database
        String dbHostname = null;
        String dbPort = null;
        String dbName = null;

        String username = null;
        String password = null;

        String connectionURL = "jdbc:postgresql://" + dbHostname + ":" + dbPort + "/" + dbName;

        connection = DriverManager.getConnection(connectionURL, username, password);
        LOGGER.info(threadIndex + "-Connection to database is initialized!");
    }

    /**
     * gets Data from the output-topic and passes the records to a method to write it to the database
     */
    private void consumeDataFromTopic() {
        if (connection == null)
            throw new NullPointerException(threadIndex + "-Connection to database is not established yet!");
        if (consumer == null)
            throw new NullPointerException(threadIndex + "-Kafka Consumer is not initialized yet!");


        while (true) {
            //TODO poll for new data and handle it
        }
    }


    /**
     * takes a record and builds a sql statement with its data to write it to the database
     *
     * @param record the record from kafka to be written to the database
     */
    private void handleRecord(ConsumerRecord<String, String> record) {
        JsonObject object = (JsonObject) jsonParser.parse(record.value());
        String sql = extractSQLInsertFromJSON(object);

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
        } catch (SQLException e) {
            LOGGER.info(threadIndex + "-" + e.toString());
        }
    }

}

