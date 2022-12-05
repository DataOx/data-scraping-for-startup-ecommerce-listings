package com.dataox.datascraper.api;

import com.dataox.datascraper.decl.mainservice.ReportManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("report")
public class ReportController {

    private final ReportManager reportManager;

    @GetMapping
    public void initReport() {
        reportManager.createReport();
    }
}
