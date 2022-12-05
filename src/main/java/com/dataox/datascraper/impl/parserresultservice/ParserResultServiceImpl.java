package com.dataox.datascraper.impl.parserresultservice;

import com.dataox.datascraper.decl.parserresultservice.ParserResultService;
import com.dataox.datascraper.domain.ScraperTask;
import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParserResultServiceImpl implements ParserResultService {

    private final ParserResultRepository repository;

    public ParserResult saveParserResponse(ParserResult result) {
        result.setCreatedAt(Instant.now());
        return repository.save(result);
    }

    @Override
    public List<ParserResult> getAllByTask(ScraperTask task) {
        return repository.findAllByTask(task);
    }

}