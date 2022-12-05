package com.dataox.datascraper.scraper.fetcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FetcherUtils {

    private FetcherUtils() {
    }

    public static void pause(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.warn("Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
