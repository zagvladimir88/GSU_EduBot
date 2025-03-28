package com.zagvladimir.command;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.zagvladimir.util.BotCommands.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandFactory {
    private final StartCommand startCommand;
    private final GroupCommand groupCommand;
    private final NotificationCommand notificationCommand;
    
    private final Map<String, Command> commandMap = new HashMap<>();

    @PostConstruct
    public void init() {
        commandMap.put(COMMAND_START, startCommand);
        commandMap.put(COMMAND_GET_GROUP, groupCommand);
        commandMap.put(COMMAND_SET_GROUP, groupCommand);
        commandMap.put(COMMAND_SCHEDULE, groupCommand);
        commandMap.put(NOTIFICATIONS, notificationCommand);
    }

    public Command getCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, message -> {
            log.warn("Unknown command received: {}", commandIdentifier);
        });
    }
} 