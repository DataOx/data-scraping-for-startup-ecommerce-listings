package com.dataox.datascraper.scraper.parser.dto;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParserResponse {

    private ParserResult attributes;

    private String error;

}