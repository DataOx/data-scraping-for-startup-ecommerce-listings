package com.dataox.datascraper.impl.parserservice;

import com.dataox.datascraper.decl.SourceType;
import com.dataox.datascraper.decl.parserresultservice.ParserResultManager;
import com.dataox.datascraper.decl.parserservice.ParserService;
import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.dto.ParserRequest;
import com.dataox.datascraper.scraper.parser.parserfactories.abelcine.AbelcineCameraAccessoriesAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.abelcine.AbelcineCameraAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.abelcine.AbelcineLensesAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.abelcine.AbelcineLightingAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.adorama.AdoramaCameraAccessoriesAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.adorama.AdoramaLensesAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.adorama.AdoramaLightingAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.bandh.BandhCameraAccessoriesAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.bandh.BandhCameraAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.bandh.BandhLensesAttributesDataParser;
import com.dataox.datascraper.scraper.parser.parserfactories.bandh.BandhLightingAttributesDataParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ParserServiceImpl implements ParserService {

    private final BandhCameraAttributesDataParser bandhCameraAttributesParser;
    private final BandhLensesAttributesDataParser bandhLensesAttributesParser;
    private final BandhLightingAttributesDataParser bandhLightingAttributesParser;
    private final BandhCameraAccessoriesAttributesDataParser bandhCameraAccessoriesAttributesParser;
    private final AbelcineCameraAttributesDataParser abelcineCameraAttributesDataParser;
    private final AbelcineLensesAttributesDataParser abelcineLensesAttributesDataParser;
    private final AbelcineLightingAttributesDataParser abelcineLightingAttributesDataParser;
    private final AbelcineCameraAccessoriesAttributesDataParser abelcineCameraAccessoriesAttributesDataParser;
    private final AdoramaLensesAttributesDataParser adoramaLensesAttributesDataParser;
    private final AdoramaLightingAttributesDataParser adoramaLightingAttributesDataParser;
    private final AdoramaCameraAccessoriesAttributesDataParser adoramaCameraAccessoriesAttributesDataParser;
    private final ParserResultManager parserResultManager;

    public void parseParserRequests(List<ParserRequest> parserRequests) {
        for (ParserRequest request : parserRequests) {
            parserResultManager.manageResult(parseParserRequestData(request), request.getUrl());
        }
        log.info("Parse successful.");
    }

    private ParserResult parseParserRequestData(ParserRequest parserRequest) {
        ParserResult parserResult = new ParserResult();
        if (parserRequest.getData() == null || parserRequest.getData().length() < 100) {
            parserResult.setError(String.valueOf(new UnsupportedOperationException()));
            return parserResult;
        } else {
            try {
                switch (parserRequest.getScraperType()) {
                    case BHPHOTOVIDEO:
                        parserResult = parseBandh(parserRequest);
                        break;
                    case ABELCINE:
                        parserResult = parseAbelcine(parserRequest);
                        break;
                    case ADORAMA:
                        parserResult = parseAdorama(parserRequest);
                        break;
                    case UNKNOWN:
                        parserResult.setError(String.valueOf(new UnsupportedOperationException()));
                        break;
                    default:
                        break;
                }
                return parserResult;
            } catch (Exception e) {
                log.info("Parsing error.");
                log.info(e);
                parserResult.setError(String.valueOf(e));
                return parserResult;
            }
        }
    }

    private ParserResult parseBandh(ParserRequest parserRequest) {
        SourceType dataType = parserRequest.getSourceType();
        switch (dataType) {
            case ACCESSORIES:
                return bandhCameraAccessoriesAttributesParser.parse(parserRequest);
            case CAMERA:
                return bandhCameraAttributesParser.parse(parserRequest);
            case LENSES:
                return bandhLensesAttributesParser.parse(parserRequest);
            case LIGHTING:
                return bandhLightingAttributesParser.parse(parserRequest);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private ParserResult parseAbelcine(ParserRequest parserRequest) {
        SourceType dataType = parserRequest.getSourceType();
        switch (dataType) {
            case ACCESSORIES:
                return abelcineCameraAccessoriesAttributesDataParser.parse(parserRequest);
            case CAMERA:
                return abelcineCameraAttributesDataParser.parse(parserRequest);
            case LENSES:
                return abelcineLensesAttributesDataParser.parse(parserRequest);
            case LIGHTING:
                return abelcineLightingAttributesDataParser.parse(parserRequest);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private ParserResult parseAdorama(ParserRequest parserRequest) {
        SourceType dataType = parserRequest.getSourceType();
        switch (dataType) {
            case ACCESSORIES:
                return adoramaCameraAccessoriesAttributesDataParser.parse(parserRequest);
            case LENSES:
                return adoramaLensesAttributesDataParser.parse(parserRequest);
            case LIGHTING:
                return adoramaLightingAttributesDataParser.parse(parserRequest);
            default:
                throw new UnsupportedOperationException();
        }
    }

}