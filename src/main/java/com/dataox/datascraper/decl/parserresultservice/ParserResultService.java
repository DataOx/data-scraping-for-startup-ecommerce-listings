package com.dataox.datascraper.decl.parserresultservice;

import com.dataox.datascraper.domain.ScraperTask;
import com.dataox.datascraper.scraper.parser.attributes.ParserResult;

import java.util.List;

public interface ParserResultService {

    ParserResult saveParserResponse(ParserResult attributes);

    List<ParserResult> getAllByTask(ScraperTask task);

}