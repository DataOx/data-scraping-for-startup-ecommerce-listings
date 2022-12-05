package com.dataox.datascraper.impl.mainservice;

import com.dataox.datascraper.decl.fetcherservice.FetcherService;
import com.dataox.datascraper.decl.taskpool.TaskPool;
import com.dataox.datascraper.domain.ScraperTask;
import com.dataox.datascraper.scraper.fetcher.FetcherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
class FetcherTaskManager {

    private final FetcherService fetcherService;

    private final TaskPool taskPoolFetch;

    public void fetch(List<ScraperTask> tasks) {
        Set<ScraperTask> newTasksForFetch = taskPoolFetch.filterNotContainsInPool(tasks);
        taskPoolFetch.addToPool(newTasksForFetch);

        tasks.stream().map(this::createRequest).forEach(fetcherService::fetch);

        taskPoolFetch.removeFromPool(newTasksForFetch);
    }

    private FetcherRequest createRequest(ScraperTask task) {
        FetcherRequest request = new FetcherRequest();
        request.setUrl(task.getSourceAddress());
        request.setType(task.getScraperType());
        return request;
    }
}
