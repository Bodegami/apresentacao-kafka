package br.com.zup.kafka.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic}")
    private final String topicName;

    @Value("${spring.kafka.partitions}")
    private final Integer partitionsNumber;

    @Value("${spring.kafka.replicas}")
    private final short replicasNumber;


    public KafkaTopicConfig(String topicName, Integer partitionsNumber, short replicasNumber) {
        this.topicName = topicName;
        this.partitionsNumber = partitionsNumber;
        this.replicasNumber = replicasNumber;
    }

    @Bean
    public NewTopic create(String topicName) {
        return new NewTopic(topicName, partitionsNumber, replicasNumber);
    }
}
