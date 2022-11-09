package br.com.zup.kafka.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    private String topic;

    private String partitions;

    private String replicas;

    @Value("${kafka.exemplo1.retention.minutes}")
    private String retentionInMinutes;


    public KafkaTopicConfig(@Value("${kafka.exemplo1.topic}") String topic,
                            @Value("${kafka.exemplo1.partitions}") String partitions,
                            @Value("${kafka.exemplo1.replicas}") String replicas) {
        this.topic = topic;
        this.partitions = partitions;
        this.replicas = replicas;
    }

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(topic)
                .partitions(Integer.parseInt(partitions))
                .replicas(Integer.parseInt(replicas))
                .config(TopicConfig.RETENTION_MS_CONFIG, retentionInMinutes)
                .build();
    }
}
