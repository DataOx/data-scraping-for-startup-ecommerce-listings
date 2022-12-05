package com.dataox.datascraper.impl.taskservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.domain.ScraperTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface ScraperTaskRepo extends JpaRepository<ScraperTask, Long> {
    List<ScraperTask> findAllByFlowStatus(FlowStatus status);

    Optional<ScraperTask> findBySourceAddress(String sourceAddress);
}
