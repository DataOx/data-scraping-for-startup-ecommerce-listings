package com.dataox.datascraper.config.sections;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("app.proxy")
public class ProxyConfig {

    private String host;

    private Integer port;
}
