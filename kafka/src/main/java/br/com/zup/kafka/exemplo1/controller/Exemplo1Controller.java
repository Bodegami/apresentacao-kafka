package br.com.zup.kafka.exemplo1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/exemplo1")
public class Exemplo1Controller {

    @PostMapping
    public String sendMessage(@RequestBody Map<String, Object> data) {
        //TODO
        return data.toString();
    }

}
