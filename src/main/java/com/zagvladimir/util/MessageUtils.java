package com.zagvladimir.util;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageUtils {

    public SendMessage createSendMessage(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(update.getMessage().getText())
                .build();
    }

    public String extractCommand(SendMessage message) {
        String[] parts = message.getText().split(" ");
        return parts[0];
    }

    public SendMessage createWelcomeMessage(String chatId) {
        var text = """
                Добро пожаловать в GSU_EduBot!

                Доступные команды:
                /start - Начало работы
                /get_groups - получить список групп
                /set_group - Настройка учебной группы; Подписка на получения расписания
                /get_schedule – Получение расписания на текущий день для установленной группы
                /notifications - Управление уведомлениями о расписании и изменениях
                """;
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
    }
} 