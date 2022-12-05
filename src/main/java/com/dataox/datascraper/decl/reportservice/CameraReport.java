package com.dataox.datascraper.decl.reportservice;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CameraReport implements CsvBean {

    @CsvBindByName(column = "Title")
    @CsvBindByPosition(position = 0)
    private String titleTask;

    @CsvBindByName(column = "Bin")
    @CsvBindByPosition(position = 1)
    private String binTask;

    @CsvBindByName(column = "Brand")
    @CsvBindByPosition(position = 2)
    private String brandTask;

    @CsvBindByName(column = "Price")
    @CsvBindByPosition(position = 3)
    private String price;

    @CsvBindByName(column = "Description")
    @CsvBindByPosition(position = 4)
    private String description;

    @CsvBindByName(column = "Website")
    @CsvBindByPosition(position = 5)
    private String url;

    @CsvBindByName(column = "Manufacturer")
    @CsvBindByPosition(position = 6)
    private String manufacturer;

    @CsvBindByName(column = "Manufacturer Part #")
    @CsvBindByPosition(position = 7)
    private String manufacturerPart;

    @CsvBindByName(column = "Weight (lb)")
    @CsvBindByPosition(position = 8)
    private String weight;

    @CsvBindByName(column = "Dimensions")
    @CsvBindByPosition(position = 9)
    private String dimensions;

    @CsvBindByName(column = "Description")
    @CsvBindByPosition(position = 10)
    private String descriptionCopy = this.description;

    @CsvBindByName(column = "Image 1")
    @CsvBindByPosition(position = 11)
    private String image1;

    @CsvBindByName(column = "Image 2")
    @CsvBindByPosition(position = 12)
    private String image2;

    @CsvBindByName(column = "Image 3")
    @CsvBindByPosition(position = 13)
    private String image3;

    @CsvBindByName(column = "Image 4")
    @CsvBindByPosition(position = 14)
    private String image4;

    @CsvBindByName(column = "Image 5")
    @CsvBindByPosition(position = 15)
    private String image5;

    @CsvBindByName(column = "Image 6")
    @CsvBindByPosition(position = 16)
    private String image6;

    @CsvBindByName(column = "Image 7")
    @CsvBindByPosition(position = 17)
    private String image7;

    @CsvBindByName(column = "Image 8")
    @CsvBindByPosition(position = 18)
    private String image8;

    @CsvBindByName(column = "Image 9")
    @CsvBindByPosition(position = 19)
    private String image9;

    @CsvBindByName(column = "Image 10")
    @CsvBindByPosition(position = 20)
    private String image10;

    @CsvBindByName(column = "Mount")
    @CsvBindByPosition(position = 21)
    private String mount;

    @CsvBindByName(column = "Sensor Type")
    @CsvBindByPosition(position = 22)
    private String sensorType;

    @CsvBindByName(column = "Sensor Size")
    @CsvBindByPosition(position = 23)
    private String sensorSize;

    @CsvBindByName(column = "Media Type")
    @CsvBindByPosition(position = 24)
    private String mediaType;

    @CsvBindByName(column = "ISO Range")
    @CsvBindByPosition(position = 25)
    private String isoRange;

    @CsvBindByName(column = "Recording Resolution")
    @CsvBindByPosition(position = 26)
    private String recordingResolution;

    @CsvBindByName(column = "Recording Format")
    @CsvBindByPosition(position = 27)
    private String recordingFormat;

    @CsvBindByName(column = "Video Output Type")
    @CsvBindByPosition(position = 28)
    private String videoOutputType;

    @CsvBindByName(column = "Headphone Out")
    @CsvBindByPosition(position = 29)
    private String headphoneOut;

    @CsvBindByName(column = "Audio I/O")
    @CsvBindByPosition(position = 30)
    private String audioIO;

    @CsvBindByName(column = "Monitor Size")
    @CsvBindByPosition(position = 31)
    private String monitorSize;

    @CsvBindByName(column = "Monitor Type")
    @CsvBindByPosition(position = 32)
    private String monitorType;

    @CsvBindByName(column = "Monitor Options")
    @CsvBindByPosition(position = 33)
    private String monitorOptions;

    @CsvBindByName(column = "Power Input")
    @CsvBindByPosition(position = 34)
    private String powerInput;

    @CsvBindByName(column = "Color, Gamma Modes")
    @CsvBindByPosition(position = 35)
    private String colorGammaModes;

    @CsvBindByName(column = "Color, Gamut")
    @CsvBindByPosition(position = 36)
    private String colorGamut;

    @CsvBindByName(column = "Grip Control Type")
    @CsvBindByPosition(position = 37)
    private String gripControlType;

    @CsvBindByName(column = "Recorder Channel Count")
    @CsvBindByPosition(position = 38)
    private String recorderChannelCount;

    @CsvBindByName(column = "Recording Resolution")
    @CsvBindByPosition(position = 39)
    private String recordingResolutionCopy = this.recordingResolution;

    @CsvBindByName(column = "System Frequency")
    @CsvBindByPosition(position = 40)
    private String systemFrequency;

    @CsvBindByName(column = "Recording Format")
    @CsvBindByPosition(position = 41)
    private String recordingFormatCopy = this.recordingFormat;

    @CsvBindByName(column = "Max Record Time")
    @CsvBindByPosition(position = 42)
    private String maxRecordTime;

    @CsvBindByName(column = "Media Type")
    @CsvBindByPosition(position = 43)
    private String mediaTypeCopy = this.mediaType;

    @CsvBindByName(column = "Sensor Sensitivity")
    @CsvBindByPosition(position = 44)
    private String sensorSensitivity;

    @CsvBindByName(column = "Sensor Signal-to-Noise Ratio")
    @CsvBindByPosition(position = 45)
    private String sensorSignalToNoiseRatio;

    @CsvBindByName(column = "Sensor Dynamic Range")
    @CsvBindByPosition(position = 46)
    private String sensorDynamicRange;

    @CsvBindByName(column = "Sensor Dimensions")
    @CsvBindByPosition(position = 47)
    private String sensorDimensions;

    @CsvBindByName(column = "Stabilization")
    @CsvBindByPosition(position = 48)
    private String stabilization;

    @CsvBindByName(column = "Timecode")
    @CsvBindByPosition(position = 49)
    private String timecode;

    @CsvBindByName(column = "Viewfinder Features")
    @CsvBindByPosition(position = 50)
    private String viewfinderFeatures;

    @CsvBindByName(column = "Wireless Features")
    @CsvBindByPosition(position = 51)
    private String wirelessFeatures;

    @CsvBindByName(column = "Shutter Speed Range")
    @CsvBindByPosition(position = 52)
    private String shutterSpeedRange;

    @CsvBindByName(column = "Shutter/Camera Mode")
    @CsvBindByPosition(position = 53)
    private String shutterCameraMode;

    @CsvBindByName(column = "Key Features")
    @CsvBindByPosition(position = 54)
    private String keyFeatures;

}
