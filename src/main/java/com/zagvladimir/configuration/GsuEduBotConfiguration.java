package com.zagvladimir.configuration;

import com.zagvladimir.bot.GguEduBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class GsuEduBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(GguEduBot gguEduBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(gguEduBot);
        return api;
    }
}
