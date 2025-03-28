package com.zagvladimir.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.zagvladimir.bot.GguEduBot;
import com.zagvladimir.util.MessageUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command {
    private final GguEduBot gguEduBot;
    private final MessageUtils messageUtils;

    @Override
    public void execute(SendMessage message) {
        SendMessage welcomeMessage = messageUtils.createWelcomeMessage(message.getChatId());
        gguEduBot.sendAnswerMessage(welcomeMessage);
    }
} 