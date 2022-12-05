package com.dataox.datascraper.scraper.parser.parserfactories.bandh;

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
public class BandhLightingAttributesDataParser implements BandhDataParser {

    @Override
    public ParserResult parse(ParserRequest request) {
        Document doc = Jsoup.parse(request.getData());
        Element body = doc.body();
        ParserResult result = ParserResult.builder()
                .manufacturer(findManufacturer(body))
                .manufacturerPart(findManufacturerPart(body))
                .weight(findValueFromSpecsField(body, "Weight"))
                .price(findPrice(body))
                .dimensions(findValueFromSpecsField(body, "Dimensions"))
                .description(findDescription(body))
                .keyFeatures(findKeyFeatures(body, request.getData()))
                .model(findValueFromSpecsField(body, "Model"))
                .photometrics(findValueFromSpecsField(body, "Photometrics"))
                .colorTemperature(findColorTemperature(body))
                .colorAccuracyStandard(findValueFromSpecsField(body, "Color Accuracy Standard"))
                .coolingSystem(findValueFromSpecsField(body, "Cooling System"))
                .dimming(findValueFromSpecsField(body, "Dimming"))
                .display(findValueFromSpecsField(body, "Display"))
                .fixtureMount(findValueFromSpecsField(body, "Fixture Mount"))
                .housingMaterial(findValueFromSpecsField(body, "Housing Material"))
                .beamAngle(findValueFromSpecsField(body, "Beam Angle"))
                .powerInputConnection(findValueFromSpecsField(body, "Power Input Connection"))
                .yokeMount(findValueFromSpecsField(body, "Yoke Mount"))
                .accessoryMount(findValueFromSpecsField(body, "Accessory Mount"))
                .remoteControlType(findValueFromSpecsField(body, "Remote Control Type"))
                .ACInputPower(findValueFromSpecsField(body, "AC Input Power"))
                .DCInputPower(findValueFromSpecsField(body, "DC Input Power"))
                .powerSource(findValueFromSpecsField(body, "Power Source"))
                .numberOfCConductors(findValueFromSpecsField(body, "Number of Conductors"))
                .maxPowerConsumption(findValueFromSpecsField(body, "Max Power Consumption"))
                .connector1(findValueFromSpecsField(body, "Connector 1"))
                .connector2(findValueFromSpecsField(body, "Connector 2"))
                .output1(findValueFromSpecsField(body, "Output 1"))
                .output2(findValueFromSpecsField(body, "Output 2"))
                .type(findValueFromSpecsField(body, "Type"))
                .material(findMaterials(body))
                .compatibility(findValueFromSpecsField(body, "Compatibility"))
                .cableLength(findValueFromSpecsField(body, "Cable Length"))
                .circuitBreaker(findValueFromSpecsField(body, "Circuit Breaker"))
                .wireGuage(findValueFromSpecsField(body, "Wire Guage"))
                .certifications(findValueFromSpecsField(body, "Certifications"))
                .lightReduction(findValueFromSpecsField(body, "Light Reduction"))
                .shape(findValueFromSpecsField(body, "Shape"))
                .ports(findValueFromSpecsField(body, "Ports"))
                .channels(findValueFromSpecsField(body, "Channels"))
                .wirelessRange(findValueFromSpecsField(body, "Wireless Range"))
                .frequency(findValueFromSpecsField(body, "Frequency"))
                .antennaGain(findValueFromSpecsField(body, "Antenna Gain"))
                .capacity(findValueFromSpecsField(body, "Capacity"))
                .build();

        result = findImages(body, result);
        return result;
    }

    private String findMaterials(Element body) {
        Elements elements = body.select("tr.pair_fDXOr6EpR9");
        String material = null;
        for (Element element : elements) {
            if (element.text().contains("Material") || element.text().contains("Materials"))
                material = element.select("td.value_fDXOr6EpR9").text();
        }
        return material;
    }

    private String findColorTemperature(Element body) {
        Elements elements = body.select("tr.pair_fDXOr6EpR9");
        String color = null;
        for (Element element : elements) {
            if (element.text().contains("Color Temperature") || element.text().contains("Color Modes"))
                color = element.select("td.value_fDXOr6EpR9").text();
        }
        return color;
    }

}
