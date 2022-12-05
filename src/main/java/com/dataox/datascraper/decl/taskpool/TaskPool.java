package com.dataox.datascraper.decl.taskpool;

import com.dataox.datascraper.domain.ScraperTask;

import java.util.List;
import java.util.Set;

public interface TaskPool {

    void addToPool(Set<ScraperTask> taskSet);

    void removeFromPool(Set<ScraperTask> taskList);

    Set<ScraperTask> filterNotContainsInPool(List<ScraperTask> taskList);
}
