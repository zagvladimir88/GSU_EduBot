package com.zagvladimir.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import com.zagvladimir.service.UpdateProducer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationCommand implements Command {
    public static final String NOTIFICATION_QUEUE = "NOTIFICATION";
    private final UpdateProducer updateProducer;

    @Override
    public void execute(SendMessage message) {
        updateProducer.produce(NOTIFICATION_QUEUE, message);
    }
} 