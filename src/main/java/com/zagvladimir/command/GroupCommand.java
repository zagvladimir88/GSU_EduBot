package com.zagvladimir.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import com.zagvladimir.service.UpdateProducer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GroupCommand implements Command {
    public static final String GROUPS_QUEUE = "GROUPS_QUEUE";
    private final UpdateProducer updateProducer;

    @Override
    public void execute(SendMessage message) {
        updateProducer.produce(GROUPS_QUEUE, message);
    }
} 