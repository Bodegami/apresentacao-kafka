package br.com.zup.kafka.exemplo1.controller;

import br.com.zup.kafka.exemplo1.repositories.IKafkaProducerExemplo1;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/exemplo1")
public class Exemplo1Controller {

    private IKafkaProducerExemplo1 producer;

    public Exemplo1Controller(IKafkaProducerExemplo1 producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestBody Map<String, Object> data) {
        producer.send(data);
        return data.toString();
    }

}
