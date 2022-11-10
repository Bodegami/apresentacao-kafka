package br.com.zup.kafka.exemplo1.repositories;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IKafkaProducer {

    void send(Map<String, Object> data);

}
