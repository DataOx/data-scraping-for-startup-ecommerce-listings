package com.dataox.datascraper.impl.parserresultservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.decl.mainservice.TaskUpdateManager;
import com.dataox.datascraper.decl.parserresultservice.ParserResultManager;
import com.dataox.datascraper.decl.parserresultservice.ParserResultService;
import com.dataox.datascraper.domain.ScraperTask;
import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ParserResultManagerImpl implements ParserResultManager {

    private final ParserResultService resultService;

    private final TaskUpdateManager taskUpdateManager;

    public void manageResult(ParserResult parserResult, String url) {
        ScraperTask task = updateAndGetTask(parserResult, url);
        parserResult.setTask(task);
        try {
            resultService.saveParserResponse(parserResult);
        } catch (Exception e) {
            log.info(ToStringBuilder.reflectionToString(parserResult));
            log.info(e);
        }
    }

    private ScraperTask updateAndGetTask(ParserResult parserResult, String url) {
        if (StringUtils.isNotBlank(parserResult.getError()))
            return taskUpdateManager.updateFlowStatus(url, FlowStatus.ERROR_PARSING);
        return taskUpdateManager.updateFlowStatus(url, FlowStatus.PARSED);
    }

}