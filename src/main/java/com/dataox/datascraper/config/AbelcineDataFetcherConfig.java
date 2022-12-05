package com.dataox.datascraper.config;

import com.dataox.datascraper.scraper.fetcher.abelcine.AbelcineDataFetcher;
import com.squareup.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AbelcineDataFetcherConfig {

    private final OkHttpClient client;

    @Bean
    public AbelcineDataFetcher getAbelcineDataFetcher() {
        return new AbelcineDataFetcher(client);
    }
}
