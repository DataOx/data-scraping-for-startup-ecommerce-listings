package com.dataox.datascraper.scraper.fetcher.bandh;

import com.dataox.datascraper.config.ChromeWebDriverProvider;
import com.dataox.datascraper.scraper.fetcher.DataFetcher;
import com.dataox.datascraper.scraper.fetcher.FetcherRequest;
import com.dataox.datascraper.scraper.fetcher.FetcherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.dataox.datascraper.scraper.fetcher.FetcherUtils.pause;


@Slf4j
@RequiredArgsConstructor
public class BandhDataFetcher implements DataFetcher {

    private final ChromeWebDriverProvider driverProvider;

    @Override
    public FetcherResponse fetch(FetcherRequest request) {
        WebDriver webDriver = driverProvider.get();
        webDriver.get(request.getUrl());

        pause(RandomUtils.nextLong(500, 1000));

        FetcherResponse response = createResponse(webDriver);

        quitWebdriver(webDriver);

        return response;
    }

    private FetcherResponse createResponse(WebDriver webDriver) {
        FetcherResponse response = new FetcherResponse();
        response.setData(webDriver.getPageSource());
        response.setError(getError(webDriver));
        return response;
    }

    private void quitWebdriver(WebDriver webDriver) {
        webDriver.close();
        webDriver.quit();
    }

    private String getError(WebDriver webDriver) {
        if (webDriver.getPageSource().contains("px-captcha"))
            return "blocked by px-captcha";

        if (!isTitleExist(webDriver))
            return "no title";

        return null;
    }

    private boolean isTitleExist(WebDriver webDriver) {
        List<WebElement> elements = webDriver.findElements(By.cssSelector("[data-selenium=productTitle]"));
        return !elements.isEmpty();
    }
}
