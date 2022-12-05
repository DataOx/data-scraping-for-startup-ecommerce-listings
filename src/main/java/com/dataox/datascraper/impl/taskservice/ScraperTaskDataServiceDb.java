package com.dataox.datascraper.impl.taskservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.domain.ScraperTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ScraperTaskDataServiceDb {

    private final ScraperTaskRepo scraperTaskRepo;

    public ScraperTask upsert(ScraperTask task) {
        return scraperTaskRepo.saveAndFlush(task);
    }

    public List<ScraperTask> getAllByStatus(FlowStatus status) {
        return scraperTaskRepo.findAllByFlowStatus(status);
    }

    public Optional<ScraperTask> findByAddress(String address) {
        return scraperTaskRepo.findBySourceAddress(address);
    }
}
