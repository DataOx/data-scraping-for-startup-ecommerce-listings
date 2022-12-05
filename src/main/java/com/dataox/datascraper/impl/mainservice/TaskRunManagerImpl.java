package com.dataox.datascraper.impl.mainservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.decl.mainservice.TaskRunManager;
import com.dataox.datascraper.decl.parserservice.ParserTaskManager;
import com.dataox.datascraper.decl.taskservice.ScraperTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TaskRunManagerImpl implements TaskRunManager {

    private final ScraperTaskService taskService;

    private final FetcherTaskManager fetcherTaskManager;

    private final ParserTaskManager parserTaskManager;

    public void fetch() {
        fetcherTaskManager.fetch(taskService.getAllByStatus(FlowStatus.PENDING));
    }

    public void parse() {
        parserTaskManager.parse(taskService.getAllByStatus(FlowStatus.FETCHED));
    }

}