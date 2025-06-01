package com.zagvladimir.util;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageUtils {

    public SendMessage createSendMessage(Long chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .parseMode("Markdown")
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
                /set_group - Подписка на получения расписания прим. /set_group ПОЗ-51
                /get_schedule – Получение расписания на текущий день для установленной группы
                /get_weekly_schedule - Получение расписания на неделю для установленной группы\s
                """;
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
    }
}