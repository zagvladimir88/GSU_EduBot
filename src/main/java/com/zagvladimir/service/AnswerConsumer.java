package com.zagvladimir.service;

import com.zagvladimir.bot.GguEduBot;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
@Service
public class AnswerConsumer {
    public static final String ANSWER_TEXT_QUEUE = "ANSWER_TEXT";
    private final GguEduBot bot;

    @RabbitListener(queues = ANSWER_TEXT_QUEUE)
    public void consumeText(SendMessage sendMessage) {
        bot.sendAnswerMessage(sendMessage);
    }
}
