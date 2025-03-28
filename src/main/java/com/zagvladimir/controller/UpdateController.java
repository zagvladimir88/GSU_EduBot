package com.zagvladimir.controller;

import com.zagvladimir.command.Command;
import com.zagvladimir.command.CommandFactory;
import com.zagvladimir.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateController {

    private final MessageUtils messageUtils;
    private final CommandFactory commandFactory;

    public void processUpdate(Update update) {
        try {
            if (update == null) {
                log.error("Received update is null");
                return;
            }

            if (isMessageWithCommand(update)) {
                handleMessageWithCommand(update);
            }
        } catch (Exception e) {
            log.error("Error processing update: ", e);
        }
    }

    private boolean isMessageWithCommand(Update update) {
        return update.hasMessage() &&
               update.getMessage().hasText() &&
               update.getMessage().getText().startsWith("/");
    }

    private void handleMessageWithCommand(Update update) {
        SendMessage sendMessage = messageUtils.createSendMessage(update);
        handleCommand(sendMessage);
    }

    private void handleCommand(SendMessage sendMessage) {
        try {
            String commandIdentifier = messageUtils.extractCommand(sendMessage);
            Command command = commandFactory.getCommand(commandIdentifier);
            command.execute(sendMessage);
        } catch (Exception e) {
            log.error("Error handling command: ", e);
        }
    }
}
