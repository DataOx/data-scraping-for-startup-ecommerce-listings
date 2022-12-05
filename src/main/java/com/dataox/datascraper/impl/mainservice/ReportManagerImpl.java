package com.dataox.datascraper.impl.mainservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.decl.mainservice.ReportManager;
import com.dataox.datascraper.decl.parserresultservice.ParserResultService;
import com.dataox.datascraper.decl.reportservice.ReportRequest;
import com.dataox.datascraper.decl.reportservice.ReportService;
import com.dataox.datascraper.decl.taskservice.ScraperTaskService;
import com.dataox.datascraper.domain.ScraperTask;
import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ReportManagerImpl implements ReportManager {

    private final ParserResultService resultService;

    private final ScraperTaskService taskService;

    private final ReportService reportService;

    private final ReportRequestMapper mapper = Mappers.getMapper(ReportRequestMapper.class);

    @Override
    public void createReport() {
        List<ParserResult> lastResults = new ArrayList<>();

        List<ScraperTask> parsed = taskService.getAllByStatus(FlowStatus.PARSED);
        for (ScraperTask task : parsed) {
            List<ParserResult> results = resultService.getAllByTask(task);
            ParserResult lastResult = results.stream().max(Comparator
                    .comparing(ParserResult::getCreatedAt)).orElseThrow();
            lastResults.add(lastResult);
        }

        reportService.createReport(createReportRequests(lastResults));
    }



    private List<ReportRequest> createReportRequests(List<ParserResult> lastResults) {
        return lastResults.stream()
                .map(mapper::mapFromParserResult)
                .collect(Collectors.toList());
    }

    @Mapper(unmappedSourcePolicy = ReportingPolicy.WARN)
    interface ReportRequestMapper {
        @Mapping(target = "titleTask", expression = "java(source.getTask().getTitle())")
        @Mapping(target = "binTask", expression = "java(source.getTask().getBin())")
        @Mapping(target = "brandTask", expression = "java(source.getTask().getBrand())")
        @Mapping(target = "descriptionTask", expression = "java(source.getTask().getDescription())")
        @Mapping(target = "url", expression = "java(source.getTask().getSourceAddress())")
        @Mapping(target = "sourceType", expression = "java(source.getTask().getSourceType())")
        @Mapping(target = "isoRange", source = "ISORange")
        @Mapping(target = "acInputPower", source = "ACInputPower")
        @Mapping(target = "dcInputPower", source = "DCInputPower")
        ReportRequest mapFromParserResult(ParserResult source);
    }
}
