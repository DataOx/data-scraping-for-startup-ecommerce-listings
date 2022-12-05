package com.dataox.datascraper.decl.parserservice;

import com.dataox.datascraper.scraper.parser.dto.ParserRequest;

import java.util.List;

public interface ParserService {

    void parseParserRequests(List<ParserRequest> parserRequest);

}