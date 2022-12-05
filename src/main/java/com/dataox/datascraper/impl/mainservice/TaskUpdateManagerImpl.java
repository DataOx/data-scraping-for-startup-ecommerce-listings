package com.dataox.datascraper.impl.mainservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.decl.mainservice.TaskUpdateManager;
import com.dataox.datascraper.decl.taskservice.ScraperTaskService;
import com.dataox.datascraper.domain.ScraperTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TaskUpdateManagerImpl implements TaskUpdateManager {

    private final ScraperTaskService taskService;

    public ScraperTask updateFlowStatus(String sourceAddress, FlowStatus status) {
        return taskService.updateFlowStatus(sourceAddress, status);
    }

}
