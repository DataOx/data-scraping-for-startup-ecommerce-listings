package com.dataox.datascraper.config;

import com.dataox.datascraper.scraper.fetcher.bandh.BandhDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandhDataFetcherConfig {

    private final ChromeWebDriverProvider driverProvider;

    @Bean
    public BandhDataFetcher getBandhDataFetcher() {
        return new BandhDataFetcher(driverProvider);
    }
}
