package com.dataox.datascraper.scraper.parser.parserfactories.abelcine;

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
public class AbelcineLensesAttributesDataParser implements AbelcineDataParser {

    @Override
    public ParserResult parse(ParserRequest request) {
        Document doc = Jsoup.parse(request.getData());

        Element body = doc.body();
        ParserResult result = ParserResult.builder()
                .manufacturer(findManufacturer(body))
                .manufacturerPart(findManufacturerPart(body))
                .description(findDescription(body))
                .keyFeatures(findKeyFeatures(body))
                .weight(findWeight(body))
                .price(findPrice(body))
                .dimensions(findMaxDimensions(body))
                .mount(findLensCharacteristics(body, "Lens Mount:"))
                .aperture(findLensCharacteristics(body, "Aperture Range:"))
                .maxAperture(findAperture(body, "Max"))
                .minimumAperture(findAperture(body, "Min"))
                .focalLength(findLensCharacteristics(body, "Fixed Focal Length:"))
                .filterSize(findLensCharacteristics(body, "Filter Thread Size:"))
                .diameter(findLensCharacteristics(body, "Front Diameter:"))
                .angleOfView(findLensCharacteristics(body, "Max Angle of View:"))
                .zoomMethod(findLensCharacteristics(body, "Prime or zoom:"))
                .lensType(findLensCharacteristics(body, "Lens Type:"))
                .focusType(findLensCharacteristics(body, "Focus Type:"))
                .imageStabilization(findLensCharacteristics(body, "Image Stabilization:"))
                .lowDispersionElements(findLensCharacteristics(body, "Low Dispersion Elements:"))
                .minimumFocusingDistance(findLensCharacteristics(body, "Minimum Focus Distance:"))
                .compatibility(findCompatibility(body))
                .build();

        result = findImages(body, result);
        return result;
    }

    private String findLensCharacteristics(Element body, String field) {
        return findTableOfSpecs(body, "Lens Characteristics", field);
    }

    private String findMaxDimensions(Element body) {
        String dimensions = findLensCharacteristics(body, "Max Dimensions (Diameter x Length):");
        if (dimensions == null || dimensions.isBlank()) {
            dimensions = findDimensions(body);
        }
        return dimensions;
    }

    private String findAperture(Element body, String type) {
        String aperture = findLensCharacteristics(body, "Aperture Range:");
        if (aperture != null && !aperture.isBlank()) {
            if (type.equals("Max")) {
                if (aperture.contains("to "))
                    return aperture.substring(aperture.indexOf("to ") + 3);
                else
                    return aperture.substring(aperture.indexOf("-") + 1);
            } else {
                if (aperture.contains("to "))
                    return aperture.substring(0, aperture.indexOf("to "));
                else
                    return aperture.substring(0, aperture.indexOf("-"));
            }
        } else
            return aperture;
    }

    private String findWeight(Element body) {
        String weight = findShipping(body, "Weight:");
        if (weight != null && !weight.isBlank() && weight.contains("Weight")) {
            weight = weight.substring(weight.indexOf("Weight: ") + 8);
        }
        return weight;
    }

    private String findCompatibility(Element body) {
        Elements elements = body.select("h3.specification-title.description-box-includes-title");
        StringBuilder value = new StringBuilder();

        int elementCount = -1;
        for (int count = 0; count < elements.size(); count++) {
            if (elements.get(count).text().equals("Compatibility - Lens:")) {
                elementCount = count;
            }
        }
        if (elementCount >= 0) {
            body = body.select("div.f-table").get(elementCount);
            elements = body.select("div.f-tr");
            for (Element element : elements) {
                value.append(element.select("div.f-td-content").select("div.specification-value.f-td").text());
            }
        }
        return value.toString();
    }

}


