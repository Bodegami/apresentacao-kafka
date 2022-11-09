package br.com.zup.kafka.exemplo1.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class Exemplo1Producer {

    @Value("${kafka.exemplo1.topic}")
    private String topic;

    @Value("${kafka.exemplo1.partitions}")
    private String partitions;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Exemplo1Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Map<String, Object> data) {
        //TODO Criar uma interface com o contrato

        String dados = data.toString();

        String divided = divideByPartitions();

        kafkaTemplate.send(topic, divided, dados).addCallback(
                success -> System.out.format(("Topic::%s - Partition::%s\n Message: %s\n"), topic, partitions, dados),
                failure -> System.out.println("")
        );

    }

    private String divideByPartitions() {
        //TODO revisar a logica

        Short shortPartitions = Short.parseShort(this.partitions);
        Random random = new Random();

        Integer divideByPartitions = random.nextInt(100) / shortPartitions;

        return String.valueOf(divideByPartitions);
    }
}
