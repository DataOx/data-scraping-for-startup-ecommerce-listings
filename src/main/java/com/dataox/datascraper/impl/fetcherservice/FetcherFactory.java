package com.dataox.datascraper.impl.fetcherservice;

import com.dataox.datascraper.decl.ScraperType;
import com.dataox.datascraper.scraper.fetcher.DataFetcher;
import com.dataox.datascraper.scraper.fetcher.abelcine.AbelcineDataFetcher;
import com.dataox.datascraper.scraper.fetcher.adorama.AdoramaDataFetcher;
import com.dataox.datascraper.scraper.fetcher.bandh.BandhDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FetcherFactory {

    private final BandhDataFetcher bandhDataFetcher;

    private final AbelcineDataFetcher abelcineDataFetcher;

    private final AdoramaDataFetcher adoramaDataFetcher;

    public DataFetcher getFetcher(ScraperType type) {
        switch (type) {
            case BHPHOTOVIDEO:
                return bandhDataFetcher;
            case ABELCINE:
                return abelcineDataFetcher;
            case ADORAMA:
                return adoramaDataFetcher;
            case ELECTRONICS_SONY:
            case ARRI_COM:
            case USA_CANON:
            case UNKNOWN:
                throw new RuntimeException(String.format("not implemented %", type));
        }
        return null;
    }
}
