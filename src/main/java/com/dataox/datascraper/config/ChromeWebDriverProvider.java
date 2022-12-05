package com.dataox.datascraper.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class ChromeWebDriverProvider {

    private final URL remoteUrl;

    @Autowired
    private ChromeOptions options;

    public ChromeWebDriverProvider(@Value("${selenium.remoteUrl}") String remoteUrl) throws MalformedURLException {
        this.remoteUrl = new URL(remoteUrl);
    }

    public WebDriver get() {
        return new RemoteWebDriver(remoteUrl, options);
    }

    public WebDriver get(ChromeOptions options) {
        return new RemoteWebDriver(remoteUrl, options);
    }

}
