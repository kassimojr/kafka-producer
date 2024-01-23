package com.mytechlessons.producer;

import com.mytechlessons.eventos.ProdutorEvento;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AplicacaoProducer {

    public static void main(String[] args) {
        AplicacaoProducer app = new AplicacaoProducer();
        app.iniciar();
    }

    private void iniciar() {
        log.info("Iniciando a aplicação...");
        ProdutorEvento producer = new ProdutorEvento();
        producer.executar();
    }
}
