package com.dataox.datascraper.impl.fetcherresultservice;

import com.dataox.datascraper.domain.FetcherResult;
import com.dataox.datascraper.domain.ScraperTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface FetcherResultRepo extends JpaRepository<FetcherResult, Long> {
    List<FetcherResult> findAllByTask(ScraperTask task);
}
