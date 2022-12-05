package com.dataox.datascraper.decl.reportservice;

import java.util.List;

public interface ReportService {
    void createReport(List<ReportRequest> reportRequestList);
}
