package com.dataox.datascraper.config;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalChromedriverConfig {

    public LocalChromedriverConfig(@Value("${app.selenium.chromedriver.path}") String path) {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, path);
    }
}
