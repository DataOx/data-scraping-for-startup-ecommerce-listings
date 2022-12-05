package com.dataox.datascraper.config;

import com.dataox.datascraper.scraper.fetcher.adorama.AdoramaDataFetcher;
import com.squareup.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdoramaDataFetcherConfig {

    private final OkHttpClient client;

    @Bean
    public AdoramaDataFetcher getAdoramaDataFetcher() {
        return new AdoramaDataFetcher(client);
    }
}
