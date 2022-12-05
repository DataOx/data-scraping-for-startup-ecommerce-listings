package com.dataox.datascraper.impl.fetcherresultservice;

import com.dataox.datascraper.domain.FetcherResult;
import com.dataox.datascraper.domain.ScraperTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class FetcherResultDataServiceDb {

    private final FetcherResultRepo fetcherResultRepo;

    @Transactional
    public FetcherResult upsert(FetcherResult result) {
        return fetcherResultRepo.saveAndFlush(result);
    }

    @Transactional
    public List<FetcherResult> findAllByTask(ScraperTask task) {
        return fetcherResultRepo.findAllByTask(task);
    }

    @Transactional
    public List<FetcherResult> findAll() {
        return fetcherResultRepo.findAll();
    }
}
