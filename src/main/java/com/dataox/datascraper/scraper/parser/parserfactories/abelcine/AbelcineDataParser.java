package com.dataox.datascraper.scraper.parser.parserfactories.abelcine;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.parserfactories.DataParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public interface AbelcineDataParser extends DataParser {

    default String findTableOfSpecs(Element body, String tableName, String field) {
        Elements elements = body.select("h3.specification-title.description-box-includes-title");
        String value;
        int elementCount = -1;
        for (int count = 0; count < elements.size(); count++) {
            if (elements.get(count).text().equals(tableName)) {
                elementCount = count;
            }
        }
        value = findValueFromSpecsField(body, elementCount, field);
        if (value == null || value.isEmpty()) {
            value = findValueInOverview(body, field);
        }
        return value;
    }

    default String findValueFromSpecsField(Element body, int elementCount, String field) {
        String value = null;
        Elements elements;
        if (elementCount >= 0) {
            body = body.select("div.f-table").get(elementCount);
            elements = body.select("div.f-tr");
            for (Element element : elements) {
                if (element.select("div.specification-key.f-td-label").select("div.label").text().equals(field)) {
                    value = element.select("div.f-td-content").select("div.specification-value.f-td").text();
                }
            }
        }
        return value;
    }

    default String findValueInOverview(Element body, String field) {
        String value = null;
        Elements elements = body.select("div.description-tabs-item.tab-content.opened").select("div.description-tabs-content").select("div.left").select("li");
        for (Element element : elements) {
            if (element.text().contains(field)) {
                value = element.text();
            }
        }
        return value;
    }

    default String findShipping(Element body, String field) {
        return findTableOfSpecs(body, "Shipping", field);
    }

    default String findManufacturer(Element body) {
        Elements elements = body.select("div.product-detail-info-title");
        if (elements.first() != null)
            return elements.first().text();
        else
            return null;
    }

    default String findManufacturerPart(Element body) {
        Elements elements = body.select("div.product-detail--manufacturer-code.js-reload-simple");
        String value = elements.text();
        if (value.contains("Mfr. Code: "))
            return value.substring(value.indexOf("Mfr. Code: ") + "Mfr. Code: ".length());
        else
            return null;
    }

    default String findPrice(Element body) {
        Elements elements = body.select("div.product-detail--price_wrap");
        String value = elements.text();
        if (value.contains("$"))
            return value.substring(value.indexOf("$") + 1);
        else
            return null;
    }

    default String findDescription(Element body) {
        Elements elements = body.select("div.product-detail-middle--info").select("div.product-detail-description");
        return elements.text();
    }

    default String findKeyFeatures(Element body) {
        StringBuilder description = new StringBuilder();
        Elements elements = body.select("div.product-detail-bottom--info").select("ul.product-detail--highlights.main-list")
                .select("li.product-detail-highlights");
        for (Element element : elements) {
            description.append(element.text()).append("; ");
        }
        return description.toString();
    }

    default String findDimensions(Element body) {
        String width = findShipping(body, "Width:");
        String height = findShipping(body, "Height:");
        String length = findShipping(body, "Length:");
        String value = null;
        if (width != null)
            value = width + "; ";
        if (height != null)
            value = height + "; ";
        if (length != null)
            value = length + "; ";
        return value;
    }

    default ParserResult findImages(Element body, ParserResult result) {
        List<String> images = new ArrayList<>();
        for (int imageCount = 0; imageCount < 11; imageCount++) {
            images.add(null);
        }

        Elements elements = body.select("a.product-detail-gallery--item.swiper-slide");
        for (int elementCount = 0; elementCount < elements.size(); elementCount++) {
            String element = elements.get(elementCount).toString();
            String image = "https://www.abelcin.com/" + element.substring(element.indexOf("data-large=\"") + 12, element.indexOf("large.jpg") + 9);
            images.set(elementCount, image);
        }

        result.setImage1(images.get(0));
        result.setImage2(images.get(1));
        result.setImage3(images.get(2));
        result.setImage4(images.get(3));
        result.setImage5(images.get(4));
        result.setImage6(images.get(5));
        result.setImage7(images.get(6));
        result.setImage8(images.get(7));
        result.setImage9(images.get(8));
        result.setImage10(images.get(9));
        return result;
    }

}