package com.dataox.datascraper.scraper.parser.dto;

import com.dataox.datascraper.decl.ScraperType;
import com.dataox.datascraper.decl.SourceType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParserRequest {

    private String data;

    private String url;

    private ScraperType scraperType;

    private SourceType sourceType;

}