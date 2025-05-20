package com.zagvladimir.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@AllArgsConstructor
@Service
public class UpdateProducer {

    private final RabbitTemplate rabbitTemplate;

    public void produce(String rabbitQueue, SendMessage sendMessage) {
        log.info(sendMessage.getText());
        rabbitTemplate.convertAndSend(rabbitQueue, sendMessage);
    }
}
