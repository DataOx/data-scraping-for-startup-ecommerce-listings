package com.dataox.datascraper.scraper.fetcher;

public interface DataFetcher {
    FetcherResponse fetch(FetcherRequest request);
}
