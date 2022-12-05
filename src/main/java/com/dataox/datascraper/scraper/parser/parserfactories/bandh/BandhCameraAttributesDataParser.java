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
public class BandhCameraAttributesDataParser implements BandhDataParser {
    private static final String SPEC_TABLE = "div.group_fDXOr6EpR9";
    private static final String SPEC_TABLE_NAME = "div.name_fDXOr6EpR9";
    private static final String SPEC_TABLE_STRING = "tr.pair_fDXOr6EpR9";
    private static final String SPEC_TABLE_STRING_NAME = "td.label_fDXOr6EpR9";
    private static final String SPEC_TABLE_STRING_VALUE = "td.value_fDXOr6EpR9";

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
                .ISORange(findValueFromSpecsField(body, "ISO Sensitivity"))
                .sensorType(findSensorType(body))
                .sensorSize(findSensorSize(body))
                .mount(findValueFromSpecsField(body, "Lens Mount"))
                .viewfinderFeatures(findViewfinder(body))
                .recordingResolution(findValueFromSpecsField(body, "Recording Modes"))
                .mediaType(findValueFromSpecsField(body, "Media/Memory Card Slot"))
                .recordingFormat(findValueFromSpecsField(body, "Recording Modes"))
                .videoOutputType(findValueFromSpecsField(body, "Video I/O"))
                .audioIO(findHeadphones(body))
                .headphoneOut(findValueFromSpecsField(body, "Audio I/O") == null ? "No" : "Yes")
                .powerInput(findValueFromSpecsField(body, "Power I/O"))
                .wirelessFeatures(findValueFromSpecsField(body, "Wireless"))
                .colorGammaModes(findValueFromSpecsField(body, "Gamma Curve"))
                .monitorType(findMonitor(body))
                .recorderChannelCount(findValueFromSpecsField(body, "Audio Recording"))
                .maxRecordTime(findValueFromSpecsField(body, "Recording Limit"))
                .timecode(findTimecode(body))
                .sensorSensitivity(findValueFromSpecsField(body, "Sensor Sensitivity"))
                .sensorSignalToNoiseRatio(findValueFromSpecsField(body, "Sensor Signal-to-Noise Ratio"))
                .sensorDynamicRange(findValueFromSpecsField(body, "Metering Range"))
                .sensorDimensions(findValueFromSpecsField(body, "Sensor Dimensions"))
                .stabilization(findValueFromSpecsField(body, "Image Stabilization"))
                .shutterSpeedRange(findValueFromSpecsField(body, "Shutter Speed"))
                .monitorSize(findMonitorSize(body))
                .build();

        result = findImages(body, result);
        return result;
    }

    private String findMonitorSize(Element body) {
        Elements elements = body.select(SPEC_TABLE);
        String monitorSize = null;
        for (Element element : elements) {
            if (element.select(SPEC_TABLE_NAME).text().contains("Monitor")) {
                elements = element.select(SPEC_TABLE_STRING);
                for (Element monitorElement : elements) {
                    if (element.select(SPEC_TABLE_STRING_NAME).text().contains("Size")) {
                        monitorSize = monitorElement.select(SPEC_TABLE_STRING_VALUE).text();
                        break;
                    }
                }
            }
        }
        return monitorSize;
    }

    private String findMonitor(Element body) {
        Elements elements = body.select(SPEC_TABLE);
        StringBuilder monitor = null;
        for (Element element : elements) {
            if (element.select(SPEC_TABLE_NAME).text().contains("Monitor")) {
                elements = element.select(SPEC_TABLE_STRING);
                monitor = new StringBuilder();
                for (Element tableElement : elements) {
                    monitor.append(tableElement.select(SPEC_TABLE_STRING_NAME).text()).append(": ").append(tableElement.select(SPEC_TABLE_STRING_VALUE).text()).append("; ");
                }
            }
        }
        return monitor == null ? null : monitor.toString();
    }


    private String findHeadphones(Element body) {
        Elements elements = body.select(SPEC_TABLE_STRING);
        String value = null;
        for (Element element : elements) {
            if (element.text().contains("Audio I/O")) {
                value = element.select(SPEC_TABLE_STRING_VALUE).text();
                break;
            }
        }
        return value;
    }

    private String findTimecode(Element body) {
        Elements elements = body.select(SPEC_TABLE_STRING_VALUE);
        String timecode = null;
        for (Element element : elements) {
            if (element.text().contains("Timecode"))
                timecode = element.select(SPEC_TABLE_STRING_VALUE).text();
        }
        return timecode;
    }

    private String findViewfinder(Element body) {
        Elements elements = body.select(SPEC_TABLE);
        StringBuilder viewfinder = null;
        for (Element element : elements) {
            if (element.select(SPEC_TABLE_NAME).text().contains("Viewfinder")) {
                Elements table = element.select("table.table_fDXOr6EpR9");
                table = table.select(SPEC_TABLE_STRING);
                viewfinder = new StringBuilder();
                for (Element tablePair : table) {
                    viewfinder.append(tablePair.select(SPEC_TABLE_STRING_NAME).text()).append(": ").append(tablePair.select(SPEC_TABLE_STRING_VALUE).text()).append("; ");
                }
                break;
            }
        }
        return viewfinder == null ? null : viewfinder.toString();
    }

    private String findSensorSize(Element body) {
        String value = findValueFromSpecsField(body, "Sensor Type");
        if (value != null && value.matches(".*\\d.*") && value.contains("("))
            return value.substring(0, value.indexOf("("));
        else
            return null;
    }

    private String findSensorType(Element body) {
        String value = findValueFromSpecsField(body, "Sensor Type");
        if (value == null)
            return null;
        if (findSensorSize(body) == null)
            return value.substring(value.indexOf(" ") + 1);
        else
            return value.substring(value.indexOf(")") + 2);
    }

}

