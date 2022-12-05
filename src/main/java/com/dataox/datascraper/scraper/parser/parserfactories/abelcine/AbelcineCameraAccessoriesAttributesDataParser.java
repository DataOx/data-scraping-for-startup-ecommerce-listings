package com.dataox.datascraper.scraper.parser.parserfactories.abelcine;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.dto.ParserRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AbelcineCameraAccessoriesAttributesDataParser implements AbelcineDataParser {

    @Override
    public ParserResult parse(ParserRequest request) {
        Document doc = Jsoup.parse(request.getData());

        Element body = doc.body();
        return ParserResult.builder()
                .manufacturer(findManufacturer(body))
                .manufacturerPart(findManufacturerPart(body))
                .weight(findShipping(body, "Weight:"))
                .price(findPrice(body))
                .dimensions(findDimensions(body))
                .description(findDescription(body))
                .keyFeatures(findKeyFeatures(body))
                .mountType(findLensCharacteristic(body, "Lens mount:"))
                .compatibility(findCompatibility(body, "Canon:"))
                .build();
    }

    private String findLensCharacteristic(Element body, String field) {
        return findTableOfSpecs(body, "Lens Characteristics", field);
    }

    private String findCompatibility(Element body, String field) {
        return findTableOfSpecs(body, "Compatibility", field);
    }

}
