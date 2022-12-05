package com.dataox.datascraper.decl.parserresultservice;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;

public interface ParserResultManager {

    void manageResult(ParserResult parserResult, String url);

}