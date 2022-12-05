package com.dataox.datascraper.scraper.fetcher;

import com.dataox.datascraper.decl.ScraperType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetcherRequest {

    private String url;

    private ScraperType type;

}
