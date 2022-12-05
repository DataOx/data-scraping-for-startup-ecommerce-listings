package com.dataox.datascraper.impl.taskpool;

import com.dataox.datascraper.decl.taskpool.TaskPool;
import com.dataox.datascraper.domain.ScraperTask;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
class TaskPoolImpl implements TaskPool {

    private final Set<ScraperTask> scraperTaskSet;

    public TaskPoolImpl() {
        this.scraperTaskSet = new HashSet<>();
    }

    public void addToPool(Set<ScraperTask> taskSet) {
        scraperTaskSet.addAll(taskSet);
    }

    public void removeFromPool(Set<ScraperTask> taskList) {
        scraperTaskSet.removeAll(taskList);
    }

    public void removeFromPool(ScraperTask task) {
        scraperTaskSet.remove(task);
    }

    public Set<ScraperTask> filterNotContainsInPool(List<ScraperTask> taskList) {
        Set<ScraperTask> collect = new HashSet<>(taskList);
        collect.removeAll(scraperTaskSet);
        return collect;
    }
}
