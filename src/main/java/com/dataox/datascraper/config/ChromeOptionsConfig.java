package com.dataox.datascraper.config;

import com.dataox.datascraper.config.sections.ProxyConfig;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class ChromeOptionsConfig {

    private final ProxyConfig proxyConfig;

    @Bean
    public ChromeOptions options() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--incognito");
        options.addArguments("--proxy-server=" + proxyConfig.getHost() + ":" + proxyConfig.getPort());

        options.setHeadless(false);
        options.addArguments("--log-level=3");
        options.addArguments("--silent");
        options.addArguments("--allow-insecure-localhost");

        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--window-position=0,0");
        options.addArguments("--ignore-certifcate-errors");
        options.addArguments("--ignore-certifcate-errors-spki-list");

        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");

        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        options.addArguments("--blink-settings=imagesEnabled=false");

        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("w3c", false);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

        return options;
    }
}
