package com.dataox.datascraper.impl.fetcherservice;

import com.dataox.datascraper.decl.fetcherservice.FetcherService;
import com.dataox.datascraper.scraper.fetcher.DataFetcher;
import com.dataox.datascraper.scraper.fetcher.FetcherRequest;
import com.dataox.datascraper.scraper.fetcher.FetcherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
class FetcherServiceImpl implements FetcherService {

    private final FetcherFactory fetcherFactory;

    private final FetcherResultManager resultManager;

    public void fetch(FetcherRequest request) {
        DataFetcher fetcher = fetcherFactory.getFetcher(request.getType());

        if (isNull(fetcher)) {
            FetcherResponse response = new FetcherResponse();
            response.setError("unknown fetcher");
            resultManager.manageResult(response, request.getUrl());
        } else {
            resultManager.manageResult(fetcher.fetch(request), request.getUrl());
        }
    }
}
