package com.dataox.datascraper.scraper.parser.parserfactories;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.dto.ParserRequest;

public interface DataParser {

    ParserResult parse(ParserRequest request);

}