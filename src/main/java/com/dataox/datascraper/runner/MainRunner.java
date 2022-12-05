package com.dataox.datascraper.runner;

import com.dataox.datascraper.config.sections.RunnerConfig;
import com.dataox.datascraper.decl.mainservice.TaskRunManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainRunner implements ApplicationRunner {

    private final RunnerConfig runnerConfig;

    private final TaskRunManager taskRunManager;

    public void scrape() {
        taskRunManager.fetch();
        taskRunManager.parse();
    }

    @Scheduled(cron = "${scheduling.runner.cron:-}")
    public void byScheduler() {
        if (!runnerConfig.isOnStart())
            scrape();
    }

    @Override
    public void run(ApplicationArguments args) {
        if (runnerConfig.isOnStart())
            scrape();
    }
}
