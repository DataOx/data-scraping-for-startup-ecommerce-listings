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
public class BandhLensesAttributesDataParser implements BandhDataParser {

    @Override
    public ParserResult parse(ParserRequest request) {
        Document doc = Jsoup.parse(request.getData());
        Element body = doc.body();
        ParserResult result = ParserResult.builder()
                .manufacturer(findManufacturer(body))
                .manufacturerPart(findManufacturerPart(body))
                .description(findDescription(body))
                .keyFeatures(findKeyFeatures(body, request.getData()))
                .weight(findValueFromSpecsField(body, "Weight"))
                .price(findPrice(body))
                .dimensions(findValueFromSpecsField(body, "Dimensions"))
                .mount(findValueFromSpecsField(body, "Lens Mount"))
                .aperture(findAperture(body, null))
                .maxAperture(findAperture(body, "max"))
                .minimumAperture(findAperture(body, "min"))
                .focalLength(findValueFromSpecsField(body, "Focal Length"))
                .diameter(findValueFromSpecsField(body, "Front Diameter"))
                .angleOfView(findValueFromSpecsField(body, "Horizontal Angle of View"))
                .imageStabilization(findValueFromSpecsField(body, "Image Stabilization"))
                .minimumFocusingDistance(findValueFromSpecsField(body, "Minimum Focus Distance"))
                .compatibility(findCompatibility(body))
                .build();

        result = findImages(body, result);
        return result;
    }

    private String findAperture(Element body, String field) {
        Elements elements = body.select("tr.pair_fDXOr6EpR9");
        for (Element element : elements) {
            if ((field == null && element.text().contains("Aperture") && !element.text().contains("Maximum Aperture") && !element.text().contains("Minimum Aperture"))
                    || (field != null && field.equals("max") && element.text().contains("Maximum Aperture"))
                    || (field != null && field.equals("min") && element.text().contains("Minimum Aperture")))
                return element.select("td.value_fDXOr6EpR9").text();
        }
        return null;
    }

}


