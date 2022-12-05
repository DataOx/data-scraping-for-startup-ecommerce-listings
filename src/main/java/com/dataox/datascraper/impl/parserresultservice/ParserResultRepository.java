package com.dataox.datascraper.impl.parserresultservice;

import com.dataox.datascraper.domain.ScraperTask;
import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParserResultRepository extends CrudRepository<ParserResult, String> {

    List<ParserResult> findAllByTask(ScraperTask task);

}