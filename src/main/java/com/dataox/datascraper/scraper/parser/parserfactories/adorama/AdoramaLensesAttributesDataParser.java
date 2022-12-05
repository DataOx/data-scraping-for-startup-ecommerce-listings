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
public class AdoramaLensesAttributesDataParser implements AdoramaDataParser {

    @Override
    public ParserResult parse(ParserRequest request) {
        Document doc = Jsoup.parse(request.getData());
        doc.outputSettings().prettyPrint(false);

        Element body = doc.body();
        ParserResult result = ParserResult.builder()
                .manufacturer(findManufacturer(body))
                .manufacturerPart(findManufacturerPart(body))
                .description(findDescription(body))
                .weight(findTableOfSpecs(body, "Weight"))
                .price(findPrice(body))
                .dimensions(findTableOfSpecs(body, "Dimensions"))
                .description(findDescription(body))
                .keyFeatures(findKeyFeatures(body))
                .mount(findTableOfSpecs(body, "Lens Mount"))
                .aperture(findAperture(body))
                .maxAperture(findTableOfSpecs(body, "Maximum Aperture"))
                .minimumAperture(findTableOfSpecs(body, "Minimum Aperture"))
                .focalLength(findTableOfSpecs(body, "Focal Length"))
                .filterSize(findTableOfSpecs(body, "Filter Size"))
                .diameter(findTableOfSpecs(body, "Front Diameter"))
                .angleOfView(findTableOfSpecs(body, "Max. Diagonal Angle of View"))
                .zoomMethod(findTableOfSpecs(body, "Zoom Method"))
                .focusType(findTableOfSpecs(body, "Focus Type"))
                .imageStabilization(findTableOfSpecs(body, "Image Stabilization"))
                .lowDispersionElements(findTableOfSpecs(body, "Low Dispersion Elements"))
                .minimumFocusingDistance(findTableOfSpecs(body, "Minimum Focus Distance"))
                .build();

        result = findImages(body, result);
        return result;
    }

    private String findAperture(Element body) {
        StringBuilder aperture = new StringBuilder();
        String minAperture = findTableOfSpecs(body, "Minimum Aperture");
        if (minAperture != null)
            aperture.append(minAperture);
        String maxAperture = findTableOfSpecs(body, "Maximum Aperture");
        if (maxAperture != null)
            aperture.append(maxAperture);
        return aperture.toString();
    }

}


