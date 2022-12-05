package com.dataox.datascraper.impl.parserservice;

import com.dataox.datascraper.decl.fetcherresultservice.FetcherResultService;
import com.dataox.datascraper.decl.parserservice.ParserService;
import com.dataox.datascraper.decl.parserservice.ParserTaskManager;
import com.dataox.datascraper.decl.taskpool.TaskPool;
import com.dataox.datascraper.domain.ScraperTask;
import com.dataox.datascraper.scraper.parser.dto.ParserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
@RequiredArgsConstructor
public class ParserTaskManagerImpl implements ParserTaskManager {

    private final ParserService parserService;
    private final FetcherResultService fetcherResultService;
    private final TaskPool taskPoolFetch;

    public void parse(List<ScraperTask> tasks) {
        log.info("Start to parse.");
        Set<ScraperTask> newTasksForFetch = taskPoolFetch.filterNotContainsInPool(tasks);
        taskPoolFetch.addToPool(newTasksForFetch);

        for (ScraperTask task : tasks) {
            parserService.parseParserRequests((createRequest(task)));
        }

        taskPoolFetch.removeFromPool(newTasksForFetch);
    }

    private List<ParserRequest> createRequest(ScraperTask task) {
        List<ParserRequest> parserRequests = new ArrayList<>();
        fetcherResultService.getAllByTask(task).forEach(fetcherResult -> {
            ParserRequest request = new ParserRequest();
            request.setScraperType(task.getScraperType());
            request.setUrl(task.getSourceAddress());
            request.setSourceType(task.getSourceType());
            request.setData(fetcherResult.getResult());
            parserRequests.add(request);
        });
        return parserRequests;
    }

}