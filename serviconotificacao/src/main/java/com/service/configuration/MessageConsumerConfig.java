package com.service.configuration;


import com.service.event.EventOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MessageConsumerConfig {

    private static final Logger log = LoggerFactory.getLogger(MessageConsumerConfig.class);

    @Bean
    public Consumer<EventOrder> receberPedido() {
        return event -> {

            log.info("Nova mensagem recebida do Kafka!");
            log.info("Processando notificação para o Pedido ID: {}", event.id_buy());
            log.info("Usuário ID: {}", event.id_user());
            log.info("Valor Total: {}", event.total_price());
            log.info("Produtos: {}", event.items());

            log.info("Processamento da mensagem concluído.");
            log.info("------------------------------------------------------------");
        };
    }
}
