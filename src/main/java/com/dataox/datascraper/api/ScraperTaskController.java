package com.dataox.datascraper.api;

import com.dataox.datascraper.decl.taskservice.ScraperTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("task")
public class ScraperTaskController {

    private final ScraperTaskService taskService;

    @PostMapping("upsert_from_xlsx")
    public void createTaskFromXlsx(@RequestParam("file") MultipartFile file) throws IOException {
        taskService.createFromXLSXFile(file);
    }
}
