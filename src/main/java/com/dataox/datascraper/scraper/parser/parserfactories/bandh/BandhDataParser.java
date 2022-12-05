package com.dataox.datascraper.scraper.parser.parserfactories.bandh;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.parserfactories.DataParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface BandhDataParser extends DataParser {

    default String findValueFromSpecsField(Element body, String fieldName) {
        Elements elements = body.select("tr.pair_fDXOr6EpR9");
        String value = null;
        for (Element element : elements) {
            if (element.select("td.label_fDXOr6EpR9").text().contains(fieldName)) {
                value = element.select("td.value_fDXOr6EpR9").text();
                break;
            }
        }
        return value;
    }

    default String findManufacturer(Element body) {
        return body.select("img[data-selenium=authorizeDealerBrandImage]").attr("alt");
    }

    default String findManufacturerPart(Element body) {
        String value = body.select("div.code_kcQ1qlyqn6").text();
        return value.substring(value.indexOf("MFR #") + 5);
    }

    default String findPrice(Element body) {
        return body.select("div.price_L0iytPTSvv").text();
    }

    default String findDescription(Element body) {
        return body.select("div.overviewDescription_OMS5rN7R1Z").text();
    }

    default String findKeyFeatures(Element body, String dataFromResult) {
        StringBuilder value = new StringBuilder();
        String keyFeatures;
        String bodyText = body.select("div.bh-preloaded-data").toString();

        if (bodyText.isEmpty())
            bodyText = dataFromResult;
        try {
            bodyText = bodyText.substring(bodyText.indexOf("&quot;sellingPoints&quot;:[{&quot;description&quot;:&quot;") + "&quot;sellingPoints&quot;:[{&quot;description&quot;:&quot;".length(), bodyText.indexOf("&quot;}],&quot;priceInfo"));
            String[] quotes = bodyText.split("&quot;},\\{&quot;description&quot;:&quot;");
            if (quotes.length > 2) {
                for (String quote : quotes) {
                    if (!quote.isBlank())
                        value.append(quote).append("; ");
                }
            }
            keyFeatures = value.toString().replace("&quot;", "'").replace("&amp", "");
        } catch (Exception e) {
            Elements elements = body.select("li.listItem_OMS5rN7R1Z");
            for (Element element : elements) {
                value.append(element.text()).append("; ");
            }
            keyFeatures = value.toString();
        }
        if (keyFeatures.isBlank()) {
            Elements elements = body.select("li.listItem_OMS5rN7R1Z");
            for (Element element : elements) {
                value.append(element.text()).append("; ");
            }
            keyFeatures = value.toString();
        }
        return keyFeatures;
    }

    default String findCompatibility(Element body) {
        try {
            String value = body.select("div.bh-preloaded-data").toString();
            String a = "compatibleModels&quot;:[&quot;";
            value = value.substring(value.indexOf(a) + a.length(), value.indexOf("&quot;],&quot;notesInfo&quot"));
            value = Arrays.toString(value.split("&quot;,&quot;"));
            value = value.substring(1, value.length() - 1);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    default ParserResult findImages(Element body, ParserResult result) {
        String value = body.select("div.bh-preloaded-data").toString();
        List<String> images = new ArrayList<>();
        for (int imageCount = 1; imageCount <= 10; imageCount++) {
            String image = findImage(value);
            if (image != null && images.contains(image)) {
                value = value.substring(value.indexOf(image) + image.length());
                image = findImage(value);
                if (image != null && images.contains(image)) {
                    value = value.substring(value.indexOf(image) + image.length());
                    image = findImage(value);
                }
                images.add(image);
            } else
                images.add(image);
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

    private String findImage(String value) {
        String startImage = "largeImages&quot;:[{&quot;url&quot;:&quot;";
        String endImage = ".jpg&quot;,&quot;width&quot;:500";
        int start = value.indexOf(startImage);
        int end = value.indexOf(endImage, start);

        if (start < 0)
            return null;
        else if (end < 0)
            return null;
        else {
            return value.substring(start + startImage.length(), end + 4);
        }
    }

}
