package com.dataox.datascraper.scraper.parser.parserfactories.adorama;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.dto.ParserRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdoramaCameraAccessoriesAttributesDataParser implements AdoramaDataParser {

    @Override
    public ParserResult parse(ParserRequest request) {
        Document doc = Jsoup.parse(request.getData());

        Element body = doc.body();
        ParserResult result = ParserResult.builder()
                .manufacturer(findManufacturer(body))
                .manufacturerPart(findManufacturerPart(body))
                .weight(findTableOfSpecs(body, "Weight"))
                .price(findPrice(body))
                .dimensions(findTableOfSpecs(body, "Dimensions"))
                .description(findDescription(body))
                .keyFeatures(findKeyFeatures(body))
                .powerSource(findTableOfSpecs(body, "Power Source"))
                .type(findTableOfSpecs(body, "Type"))
                .cableLength(findTableOfSpecs(body, "Cable Length"))
                .capacity(findTableOfSpecs(body, "Capacity"))
                .voltage(findTableOfSpecs(body, "Voltage"))
                .batteryInfo(findTableOfSpecs(body, "Battery Info"))
                .batteryMaterial(findTableOfSpecs(body, "Battery Material"))
                .clampDiameter(findTableOfSpecs(body, "Clamp Diameter"))
                .transferSpeed(findTableOfSpecs(body, "Transfer Speed"))
                .syncSpeed(findTableOfSpecs(body, "Sync Speed"))
                .transmissionSystem(findTableOfSpecs(body, "Transmission System"))
                .transmissionRange(findTableOfSpecs(body, "Transmission Range"))
                .flashRatioControl(findTableOfSpecs(body, "Flash Ratio Control"))
                .flashCoverage(findTableOfSpecs(body, "Flash Coverage"))
                .batteryLife(findTableOfSpecs(body, "Battery Life"))
                .mountingPoint(findTableOfSpecs(body, "Mounting Point"))
                .connectionType(findTableOfSpecs(body, "Connection Type"))
                .storageCapacity(findTableOfSpecs(body, "Storage Capacity"))
                .dataTransfer(findTableOfSpecs(body, "Data Transfer"))
                .loadCapacity(findTableOfSpecs(body, "Load Capacity"))
                .mountType(findTableOfSpecs(body, "Lens mount"))
                .size(findTableOfSpecs(body, "Size"))
                .resolution(findTableOfSpecs(body, "Resolution"))
                .aspectRatio(findTableOfSpecs(body, "Aspect Ratio"))
                .inputConnectionType(findTableOfSpecs(body, "Input"))
                .outputConnectionType(findTableOfSpecs(body, "Output"))
                .audioIn(findTableOfSpecs(body, "Audio In"))
                .audioOut(findTableOfSpecs(body, "Audio Out"))
                .compatibility(findTableOfSpecs(body, "Compatibility"))
                .build();

        result = findImages(body, result);
        return result;
    }

}
