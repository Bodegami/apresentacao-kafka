package br.com.zup.kafka.exemplo2.repositories;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IKafkaProducerExemplo2 {

    void send(Map<String, Object> data);

}
