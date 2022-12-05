package com.dataox.datascraper.impl.fetcherservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.decl.fetcherresultservice.FetcherResultService;
import com.dataox.datascraper.decl.mainservice.TaskUpdateManager;
import com.dataox.datascraper.domain.FetcherResult;
import com.dataox.datascraper.domain.ScraperTask;
import com.dataox.datascraper.scraper.fetcher.FetcherResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FetcherResultManager {
    private final FetcherResultService resultService;

    private final TaskUpdateManager taskUpdateManager;

    public void manageResult(FetcherResponse result, String url) {
        ScraperTask task = updateAndGetTask(result, url);

        FetcherResult fetcherResult = new FetcherResult();
        fetcherResult.setResult(result.getData());
        fetcherResult.setResultText(result.getData());
        fetcherResult.setError(result.getError());
        fetcherResult.setTask(task);

        resultService.saveNew(fetcherResult);
    }

    private ScraperTask updateAndGetTask(FetcherResponse result, String url) {
        if (StringUtils.isNotBlank(result.getError()))
            return taskUpdateManager.updateFlowStatus(url, FlowStatus.ERROR_FETCHING);

        return taskUpdateManager.updateFlowStatus(url, FlowStatus.FETCHED);
    }
}
