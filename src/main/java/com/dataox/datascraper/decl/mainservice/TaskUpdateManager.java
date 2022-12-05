package com.dataox.datascraper.decl.mainservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.domain.ScraperTask;

public interface TaskUpdateManager {
    ScraperTask updateFlowStatus(String sourceAddress, FlowStatus status);
}
