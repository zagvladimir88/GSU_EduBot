package com.zagvladimir.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "rabbit")
public class RabbitProperties {
    private List<String> queues;
}