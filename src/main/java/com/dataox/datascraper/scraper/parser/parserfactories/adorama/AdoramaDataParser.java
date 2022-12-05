package com.dataox.datascraper.scraper.parser.parserfactories.adorama;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.parserfactories.DataParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface AdoramaDataParser extends DataParser {

    default String findTableOfSpecs(Element body, String tableName) {
        Elements elements = body.getElementsByTag("dl").select("dt");
        int elementCount = -1;
        for (int count = 0; count < elements.size(); count++) {
            if (elements.get(count).text().contains(tableName)) {
                elementCount = count;
            }
        }
        String value = null;
        if (elementCount >= 0) {
            elements = body.getElementsByTag("dl").select("dd");
            value = elements.get(elementCount).text();
        }
        return value;
    }

    default String findManufacturer(Element body) {
        Element element = body.getElementsByTag("h1").first();
        if (element != null) {
            String value = element.text();
            return value.substring(value.indexOf("\\r\\n \\r\\n \\r\\n ") + "\\r\\n \\r\\n \\r\\n ".length(), value.indexOf(" ", "\\r\\n \\r\\n \\r\\n ".length()));
        } else
            return null;
    }

    default String findManufacturerPart(Element body) {
        Elements elements = body.getElementsByTag("i");
        String value;
        for (Element element : elements) {
            value = element.text();
            if (value.contains("MFR: "))
                return value.substring(value.indexOf("MFR: ") + 5);
        }
        return null;
    }

    default String findPrice(Element body) {
        Elements elements = body.getElementsByTag("strong");
        String price = null;
        for (Element element : elements) {
            if (element.text().contains("$")) {
                price = element.text();
            }
        }
        return price;
    }

    default String findDescription(Element body) {
        Elements elements = body.getElementsByTag("h2");
        String description = null;
        int count = -1;
        for (int elementCount = 0; elementCount < elements.size(); elementCount++) {
            if (elements.get(elementCount).text().contains("About")) {
                count = elementCount;
                break;
            }
        }
        if (count >= 0) {
            Element element = elements.get(count).parent();
            if (element != null) {
                elements = element.children();
                for (Element text : elements) {
                    if (text.toString().contains("description-wrap")) {
                        description = text.getElementsByTag("p").text();
                        description = description.replace("\\r\\n", "").trim();
                    }
                }
            }
        }
        return description;
    }

    default String findKeyFeatures(Element body) {
        StringBuilder keyFeatures = new StringBuilder();
        Elements elements = body.getElementsByTag("h2");
        int count = -1;
        for (int elementCount = 0; elementCount < elements.size(); elementCount++) {
            if (elements.get(elementCount).text().contains("About")) {
                count = elementCount;
                break;
            }
        }
        if (count >= 0) {
            Element element = elements.get(count).parent();
            if (element != null) {
                elements = element.getElementsByTag("li");
                for (Element feature : elements) {
                    keyFeatures.append(feature.text()).append("; ");
                }
            }
        }
        return keyFeatures.toString();
    }

    default ParserResult findImages(Element body, ParserResult result) {
        Elements elements = body.getElementsByTag("data-has-zoom");
        if (elements.isEmpty())
            elements = body.getElementsByTag("img");

        List<String> images = new ArrayList<>();
        for (Element element : elements) {
            String value = element.toString();
            if (value.contains(".jpg")) {
                images.add(value.substring(value.indexOf("https"), value.indexOf(".jpg") + 4));
            }
        }
        images = images.stream().distinct().collect(Collectors.toList());
        result.setImage1(!images.isEmpty() ? images.get(0) : null);
        result.setImage2(images.size() > 1 ? images.get(1) : null);
        result.setImage3(images.size() > 2 ? images.get(2) : null);
        result.setImage4(images.size() > 3 ? images.get(3) : null);
        result.setImage5(images.size() > 4 ? images.get(4) : null);
        result.setImage6(images.size() > 5 ? images.get(5) : null);
        result.setImage7(images.size() > 6 ? images.get(6) : null);
        result.setImage8(images.size() > 7 ? images.get(7) : null);
        result.setImage9(images.size() > 8 ? images.get(8) : null);
        result.setImage10(images.size() > 9 ? images.get(9) : null);
        return result;
    }


}