package com.mytechlessons.eventos;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Slf4j
public class ProdutorEvento {
    public ProdutorEvento() {
        producer = criarProducer();
    }

    private final Producer<String, String> producer;

    private Producer<String, String > criarProducer() {
        if (producer != null) {
            return producer;
        }

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("serializer.class", "kafka.serializer.DefaultEncoder");

        return new KafkaProducer<>(properties);
    }

    public void executar() {
        String chave = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String mensagem = sdf.format(new Date())
                .concat("|")
                .concat(chave)
                .concat("|NOVA_MENSAGEM");

        log.info("Iniciando envio da mensagem...");
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("RegistroEvento", chave, mensagem);
        producer.send(producerRecord);
        producer.flush();
        producer.close();
        log.info("Mensagem enviada com sucesso [{}]", mensagem);
    }
}
