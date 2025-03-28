package com.zagvladimir.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Command {
    void execute(SendMessage message);
} 