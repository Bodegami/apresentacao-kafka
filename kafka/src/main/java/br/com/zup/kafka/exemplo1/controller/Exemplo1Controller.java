package br.com.zup.kafka.exemplo1.controller;

import br.com.zup.kafka.exemplo1.producer.Exemplo1Producer;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/exemplo1")
public class Exemplo1Controller {

    private Exemplo1Producer producer;

    public Exemplo1Controller(Exemplo1Producer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestBody Map<String, Object> data) {
        producer.send(data);
        return data.toString();
    }

}
