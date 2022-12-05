package com.dataox.datascraper.decl.parserservice;

import com.dataox.datascraper.domain.ScraperTask;

import java.util.List;

public interface ParserTaskManager {

    void parse(List<ScraperTask> tasks);

}