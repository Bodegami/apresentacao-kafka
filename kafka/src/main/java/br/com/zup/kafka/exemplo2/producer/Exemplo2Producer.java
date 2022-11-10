package br.com.zup.kafka.exemplo2.producer;

import br.com.zup.kafka.exemplo2.repositories.IKafkaProducerExemplo2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class Exemplo2Producer implements IKafkaProducerExemplo2 {

    private final String KEY = "partition";

    @Value("${kafka.exemplo2.topic}")
    private String topic;

    @Value("${kafka.exemplo2.partitions}")
    private String partitions;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Exemplo2Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Map<String, Object> data) {

        String dados = data.toString();

        String requestPartitionKey = requestPartition(data);

        kafkaTemplate.send(topic, requestPartitionKey, dados).addCallback(
                success -> System.out.format(("Topic:: %s - PartitionKey:: %s\n Message:: %s\n"), topic, requestPartitionKey, dados),
                failure -> System.out.println("")
        );

    }


    private String requestPartition(Map<String, Object> data) {

        if (!data.containsKey(KEY)) {
            return this.topic + "-" + this.divideByPartitions();
        }

        return (String) data.get(KEY);
    }

    private String divideByPartitions() {

        Short shortPartitions = Short.parseShort(this.partitions);
        Random random = new Random();

        Integer divideByPartitions = random.nextInt(100) % shortPartitions;

        return String.valueOf(divideByPartitions);
    }

}
