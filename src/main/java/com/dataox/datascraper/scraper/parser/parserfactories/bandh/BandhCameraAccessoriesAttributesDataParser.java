package com.dataox.datascraper.scraper.parser.parserfactories.bandh;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.dto.ParserRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BandhCameraAccessoriesAttributesDataParser implements BandhDataParser {

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
                .powerSource(findValueFromSpecsField(body, "Power Source"))
                .type(findValueFromSpecsField(body, "Type"))
                .cableLength(findValueFromSpecsField(body, "Cable Length"))
                .capacity(findValueFromSpecsField(body, "Capacity"))
                .voltage(findValueFromSpecsField(body, "Voltage"))
                .batteryInfo(findValueFromSpecsField(body, "Battery Info"))
                .batteryMaterial(findValueFromSpecsField(body, "Battery Material"))
                .clampDiameter(findValueFromSpecsField(body, "Clamp Diameter"))
                .transferSpeed(findValueFromSpecsField(body, "Transfer Speed"))
                .syncSpeed(findValueFromSpecsField(body, "Sync Speed"))
                .transmissionSystem(findValueFromSpecsField(body, "Transmission System"))
                .transmissionRange(findValueFromSpecsField(body, "Transmission Range"))
                .flashRatioControl(findValueFromSpecsField(body, "Flash Ratio Control"))
                .flashCoverage(findValueFromSpecsField(body, "Flash Coverage"))
                .batteryLife(findValueFromSpecsField(body, "Battery Life"))
                .mountingPoint(findValueFromSpecsField(body, "Mounting Point"))
                .connectionType(findValueFromSpecsField(body, "Connection Type"))
                .storageCapacity(findValueFromSpecsField(body, "Storage Capacity"))
                .dataTransfer(findValueFromSpecsField(body, "Data Transfer"))
                .loadCapacity(findValueFromSpecsField(body, "Load Capacity"))
                .mountType(findValueFromSpecsField(body, "Mount Type"))
                .size(findValueFromSpecsField(body, "Size"))
                .resolution(findValueFromSpecsField(body, "Resolution"))
                .aspectRatio(findValueFromSpecsField(body, "Aspect Ratio"))
                .inputConnectionType(findValueFromSpecsField(body, "Input Connection Type"))
                .outputConnectionType(findValueFromSpecsField(body, "Output Connection Type"))
                .audioIn(findValueFromSpecsField(body, "Audio In"))
                .audioOut(findValueFromSpecsField(body, "Audio Out"))
                .compatibility(findCompatibility(body))
                .build();

        result = findImages(body, result);
        return result;
    }

}
