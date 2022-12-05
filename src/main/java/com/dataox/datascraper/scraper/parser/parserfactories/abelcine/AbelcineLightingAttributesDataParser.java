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
public class AbelcineLightingAttributesDataParser implements AbelcineDataParser {

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
                .colorTemperature(findLighting(body, "Color Temperature:"))
                .dimming(findLighting(body, "Dimming Range:"))
                .beamAngle(findLighting(body, "Beam Angle / Light Spread:"))
                .powerInputConnection(findPower(body, "Power Input:"))
                .yokeMount(findLightingAccessories(body, "Frame / Mount / Stand:"))
                .connector1(findConnector(body, "Input Connector/Port:"))
                .connector2(findConnector(body, "Input Connector/Port:"))
                .build();
    }

    private String findLighting(Element body, String field) {
        return findTableOfSpecs(body, "Lighting", field);
    }

    private String findLightingAccessories(Element body, String field) {
        return findTableOfSpecs(body, "Lighting Accessories", field);
    }

    private String findPower(Element body, String field) {
        return findTableOfSpecs(body, "Power", field);
    }

    private String findConnector(Element body, String field) {
        return findTableOfSpecs(body, "Connectors", field);
    }

}
