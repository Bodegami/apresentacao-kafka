package br.com.zup.kafka.exemplo2.controller;

import br.com.zup.kafka.exemplo2.repositories.IKafkaProducerExemplo2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/exemplo2")
public class Exemplo2Controller {

    private IKafkaProducerExemplo2 producer;

    public Exemplo2Controller(IKafkaProducerExemplo2 producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestBody Map<String, Object> data) {

        producer.send(data);
        return data.toString();
    }

}
