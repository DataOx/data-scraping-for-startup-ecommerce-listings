package com.dataox.datascraper.decl.fetcherresultservice;

import com.dataox.datascraper.domain.FetcherResult;
import com.dataox.datascraper.domain.ScraperTask;

import java.util.List;

public interface FetcherResultService {

    FetcherResult saveNew(FetcherResult result);

    List<FetcherResult> getAllByTask(ScraperTask task);

}
