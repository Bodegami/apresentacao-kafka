package br.com.zup.kafka.exemplo1.producer;

import br.com.zup.kafka.exemplo1.repositories.IKafkaProducerExemplo1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class Exemplo1Producer implements IKafkaProducerExemplo1 {

    @Value("${kafka.exemplo1.topic}")
    private String topic;

    @Value("${kafka.exemplo1.partitions}")
    private String partitions;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Exemplo1Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Map<String, Object> data) {

        String dados = data.toString();

        String selectedPartition = topic + "-" + divideByPartitions();

        kafkaTemplate.send(topic, selectedPartition, dados).addCallback(
                success -> System.out.format(("Topic:: %s - PartitionKey:: %s\n Message:: %s\n"), topic, selectedPartition, dados),
                failure -> System.out.println("")
        );

    }

    private String divideByPartitions() {

        Short shortPartitions = Short.parseShort(this.partitions);
        Random random = new Random();

        Integer divideByPartitions = random.nextInt(100) % shortPartitions;

        return String.valueOf(divideByPartitions);
    }
}
