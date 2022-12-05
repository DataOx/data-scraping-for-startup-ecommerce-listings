package com.dataox.datascraper.scraper.parser.parserfactories.adorama;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.dto.ParserRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdoramaLightingAttributesDataParser implements AdoramaDataParser {

    @Override
    public ParserResult parse(ParserRequest request) {
        Document doc = Jsoup.parse(request.getData());

        Element body = doc.body();
        ParserResult result = ParserResult.builder()
                .manufacturer(findManufacturer(body))
                .manufacturerPart(findManufacturerPart(body))
                .weight(findWeight(body))
                .price(findPrice(body))
                .dimensions(findDimensions(body))
                .description(findDescription(body))
                .keyFeatures(findKeyFeatures(body))
                .model(findTableOfSpecs(body, "Model"))
                .photometrics(findTableOfSpecs(body, "Photometrics"))
                .colorTemperature(findTableOfSpecs(body, "Color Temperature"))
                .colorAccuracyStandard(findTableOfSpecs(body, "Color Accuracy Standard"))
                .coolingSystem(findTableOfSpecs(body, "Cooling System"))
                .dimming(findTableOfSpecs(body, "Dimming"))
                .display(findTableOfSpecs(body, "Display"))
                .fixtureMount(findTableOfSpecs(body, "Fixture Mount"))
                .housingMaterial(findTableOfSpecs(body, "Housing Material"))
                .beamAngle(findTableOfSpecs(body, "Beam Angle"))
                .powerInputConnection(findTableOfSpecs(body, "Power Input Connection"))
                .yokeMount(findTableOfSpecs(body, "Mounting"))
                .accessoryMount(findTableOfSpecs(body, "Accessory Mount"))
                .remoteControlType(findTableOfSpecs(body, "Remote Control Type"))
                .ACInputPower(findTableOfSpecs(body, "AC Input Power"))
                .DCInputPower(findTableOfSpecs(body, "DC Input Power"))
                .powerSource(findTableOfSpecs(body, "Power Source"))
                .numberOfCConductors(findTableOfSpecs(body, "Number of Conductors"))
                .maxPowerConsumption(findTableOfSpecs(body, "Power Consumption"))
                .connector1(findTableOfSpecs(body, "Connector"))
                .output1(findTableOfSpecs(body, "Output 1"))
                .output2(findTableOfSpecs(body, "Output 2"))
                .type(findTableOfSpecs(body, "Type"))
                .material(findTableOfSpecs(body, "Material"))
                .compatibility(findTableOfSpecs(body, "Compatibility"))
                .cableLength(findTableOfSpecs(body, "Cable Length"))
                .circuitBreaker(findTableOfSpecs(body, "Circuit Breaker"))
                .wireGuage(findTableOfSpecs(body, "Wire Gauge"))
                .certifications(findTableOfSpecs(body, "Certifications"))
                .lightReduction(findTableOfSpecs(body, "Light Reduction"))
                .shape(findTableOfSpecs(body, "Shape"))
                .ports(findTableOfSpecs(body, "Ports"))
                .channels(findTableOfSpecs(body, "Channels"))
                .wirelessRange(findTableOfSpecs(body, "Wireless Range"))
                .frequency(findTableOfSpecs(body, "Frequency"))
                .antennaGain(findTableOfSpecs(body, "Antenna Gain"))
                .capacity(findTableOfSpecs(body, "Capacity"))
                .build();

        result = findImages(body, result);
        return result;
    }

    private String findWeight(Element body) {
        String weight = findTableOfSpecs(body, "Weight");
        if (weight == null || weight.isBlank()) {
            Elements elements = body.getElementsByTag("li");
            for (Element element : elements) {
                if (element.text().contains("Weight")) {
                    weight = element.text().substring(element.text().indexOf("Weight: ") + "Weight: ".length());
                }
            }
        }
        return weight;
    }

    private String findDimensions(Element body) {
        String dimensions = findTableOfSpecs(body, "Dimensions");
        if (dimensions == null || dimensions.isBlank()) {
            Elements elements = body.getElementsByTag("li");
            for (Element element : elements) {
                if (element.text().contains("Dimensions")) {
                    dimensions = element.text().substring(element.text().indexOf("Dimensions: ") + "Dimensions: ".length());
                }
            }
        }
        return dimensions;
    }

}
