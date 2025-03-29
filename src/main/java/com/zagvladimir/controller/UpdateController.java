package com.zagvladimir.controller;

import com.zagvladimir.command.Command;
import com.zagvladimir.command.CommandFactory;
import com.zagvladimir.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateController {

    private final MessageUtils messageUtils;
    private final CommandFactory commandFactory;

    public void processUpdate(Update update) {
        if (update == null) {
            log.error("Получен пустой update");
            return;
        }

        try {
            if (isMessageWithCommand(update)) {
                handleMessageWithCommand(update);
            }
            if (update.hasCallbackQuery()) {
                handleCallbackQuery(update);
            }
        } catch (Exception e) {
            log.error("Ошибка при обработке update: ", e);
        }
    }

    private boolean isMessageWithCommand(Update update) {
        return update.hasMessage() &&
                update.getMessage().hasText() &&
                update.getMessage().getText().startsWith("/");
    }

    private void handleMessageWithCommand(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        processCommand(chatId, text);
    }

    private void handleCallbackQuery(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        Long chatId = callbackQuery.getMessage().getChatId();
        String data = callbackQuery.getData();
        processCommand(chatId, data);
    }

    private void processCommand(Long chatId, String commandText) {
        try {
            SendMessage sendMessage = messageUtils.createSendMessage(chatId, commandText);
            String commandIdentifier = messageUtils.extractCommand(sendMessage);
            Command command = commandFactory.getCommand(commandIdentifier);
            command.execute(sendMessage);
        } catch (Exception e) {
            log.error("Ошибка при выполнении команды: ", e);
        }
    }
}
