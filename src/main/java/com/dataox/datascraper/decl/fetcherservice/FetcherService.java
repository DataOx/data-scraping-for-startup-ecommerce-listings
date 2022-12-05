package com.dataox.datascraper.decl.fetcherservice;

import com.dataox.datascraper.scraper.fetcher.FetcherRequest;

public interface FetcherService {
    void fetch(FetcherRequest request);
}
