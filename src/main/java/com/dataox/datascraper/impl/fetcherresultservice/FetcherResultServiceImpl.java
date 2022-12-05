package com.dataox.datascraper.impl.fetcherresultservice;

import com.dataox.datascraper.decl.fetcherresultservice.FetcherResultService;
import com.dataox.datascraper.domain.FetcherResult;
import com.dataox.datascraper.domain.ScraperTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
class FetcherResultServiceImpl implements FetcherResultService {

    private final FetcherResultDataServiceDb resultDataService;

    public FetcherResult saveNew(FetcherResult result) {
        result.setCreatedAt(Instant.now());
        return resultDataService.upsert(result);
    }

    public List<FetcherResult> getAllByTask(ScraperTask task) {
        return resultDataService.findAllByTask(task);
    }

    public List<FetcherResult> getAll() {
        return resultDataService.findAll();
    }
}
