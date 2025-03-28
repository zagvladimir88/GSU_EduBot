package com.zagvladimir.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "rabbit")
public class RabbitProperties {
    Map<String,String> queues;
}