package com.dataox.datascraper.decl.taskservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.domain.ScraperTask;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ScraperTaskService {

    List<ScraperTask> getAllByStatus(FlowStatus status);

    ScraperTask updateFlowStatus(String address, FlowStatus newFlowStatus);

    void createFromXLSXFile(MultipartFile file) throws IOException;
}
