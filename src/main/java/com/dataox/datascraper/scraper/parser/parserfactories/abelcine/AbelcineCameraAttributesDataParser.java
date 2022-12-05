package com.dataox.datascraper.scraper.parser.parserfactories.abelcine;

import com.dataox.datascraper.scraper.parser.attributes.ParserResult;
import com.dataox.datascraper.scraper.parser.dto.ParserRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AbelcineCameraAttributesDataParser implements AbelcineDataParser {

    @Override
    public ParserResult parse(ParserRequest request) {
        Document doc = Jsoup.parse(request.getData());

        Element body = doc.body();
        return ParserResult.builder()
                .manufacturer(findManufacturer(body))
                .manufacturerPart(findManufacturerPart(body))
                .description(findDescription(body))
                .keyFeatures(findKeyFeatures(body))
                .weight(findShipping(body, "Weight:"))
                .price(findPrice(body))
                .dimensions(findDimensions(body))
                .ISORange(findCamera(body, "Native ISO:"))
                .sensorType(findSensorSpec(body, "Sensor Type:"))
                .sensorSize(findSensorSpec(body, "Sensor Size:"))
                .mount(findCameraSystem(body, "Camera Lens Mount:"))
                .viewfinderFeatures(findViewfinder(body, "EVF Features:"))
                .recordingResolution(findRecording(body, "Recording Resolution:"))
                .mediaType(findMedia(body, "Media Type:"))
                .recordingFormat(findRecording(body, "Recording Format:"))
                .videoOutputType(findInputsOutputs(body, "Video Output Type:"))
                .audioIO(findInputsOutputs(body, "Audio I/O:"))
                .headphoneOut(findInputsOutputs(body, "Headphone Out:"))
                .powerInput(findPower(body, "Power Input:"))
                .wirelessFeatures(findWireless(body, "Motor Control Notes:"))
                .colorGammaModes(findColor(body, "Color Gamut:"))
                .monitorType(findMonitors(body, "Monitor Type:"))
                .recorderChannelCount(findRecorders(body, "Recorder Channel Count:"))
                .maxRecordTime(findRecording(body, "Recording Max Frame Rate:"))
                .timecode(findInputsOutputs(body, "Timecode In/Out:"))
                .sensorSensitivity(findSensorSpec(body, "Sensitivity:"))
                .sensorSignalToNoiseRatio(findSensorSpec(body, "Aspect Ratio:"))
                .sensorDynamicRange(findSensorSpec(body, "Sensor Dynamic Range:"))
                .sensorDimensions(findSensorSpec(body, "Sensor Dimensions:"))
                .shutterSpeedRange(findCamera(body, "Shutter Speed Range:"))
                .monitorSize(findMonitors(body, "Monitor Size:"))
                .build();
    }

    private String findSensorSpec(Element body, String field) {
        return findTableOfSpecs(body, "Sensor Specifics", field);
    }

    private String findRecording(Element body, String field) {
        return findTableOfSpecs(body, "Recording", field);
    }

    private String findRecorders(Element body, String field) {
        return findTableOfSpecs(body, "Recorders", field);
    }

    private String findMedia(Element body, String field) {
        return findTableOfSpecs(body, "Recording / Media", field);
    }

    private String findMonitors(Element body, String field) {
        return findTableOfSpecs(body, "Monitors", field);
    }

    private String findInputsOutputs(Element body, String field) {
        return findTableOfSpecs(body, "Inputs & Outputs", field);
    }

    private String findPower(Element body, String field) {
        return findTableOfSpecs(body, "Power", field);
    }

    private String findColor(Element body, String field) {
        return findTableOfSpecs(body, "Color", field);
    }

    private String findWireless(Element body, String field) {
        return findTableOfSpecs(body, "Wireless", field);
    }

    private String findCameraSystem(Element body, String field) {
        return findTableOfSpecs(body, "Camera Lens System", field);
    }

    private String findCamera(Element body, String field) {
        return findTableOfSpecs(body, "Camera", field);
    }

    private String findViewfinder(Element body, String field) {
        return findTableOfSpecs(body, "Viewfinders", field);
    }

}

