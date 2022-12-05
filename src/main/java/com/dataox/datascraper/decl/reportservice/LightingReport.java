package com.dataox.datascraper.decl.reportservice;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightingReport implements CsvBean {

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

    @CsvBindByName(column = "Model")
    @CsvBindByPosition(position = 10)
    private String model;

    @CsvBindByName(column = "Image 1")
    @CsvBindByPosition(position = 11)
    private String image1;

    @CsvBindByName(column = "Image 2")
    @CsvBindByPosition(position =12)
    private String image2;

    @CsvBindByName(column = "Image 3")
    @CsvBindByPosition(position =13)
    private String image3;

    @CsvBindByName(column = "Image 4")
    @CsvBindByPosition(position =14)
    private String image4;

    @CsvBindByName(column = "Image 5")
    @CsvBindByPosition(position =15)
    private String image5;

    @CsvBindByName(column = "Image 6")
    @CsvBindByPosition(position =16)
    private String image6;

    @CsvBindByName(column = "Image 7")
    @CsvBindByPosition(position =17)
    private String image7;

    @CsvBindByName(column = "Image 8")
    @CsvBindByPosition(position =18)
    private String image8;

    @CsvBindByName(column = "Image 9")
    @CsvBindByPosition(position =19)
    private String image9;

    @CsvBindByName(column = "Image 10")
    @CsvBindByPosition(position =20)
    private String image10;

    @CsvBindByName(column = "Photometrics")
    @CsvBindByPosition(position =21)
    private String photometrics;

    @CsvBindByName(column = "Color Temperature")
    @CsvBindByPosition(position =22)
    private String colorTemperature;

    @CsvBindByName(column = "Color Accuracy Standard")
    @CsvBindByPosition(position =23)
    private String colorAccuracyStandard;

    @CsvBindByName(column = "Cooling System")
    @CsvBindByPosition(position =24)
    private String coolingSystem;

    @CsvBindByName(column = "Dimming")
    @CsvBindByPosition(position =25)
    private String dimming;

    @CsvBindByName(column = "Display")
    @CsvBindByPosition(position =26)
    private String display;

    @CsvBindByName(column = "Housing Material")
    @CsvBindByPosition(position =27)
    private String housingMaterial;

    @CsvBindByName(column = "Beam Angle")
    @CsvBindByPosition(position =28)
    private String beamAngle;

    @CsvBindByName(column = "Power Input Connection")
    @CsvBindByPosition(position =29)
    private String powerInputConnection;

    @CsvBindByName(column = "Yoke Mount")
    @CsvBindByPosition(position =30)
    private String yokeMount;

    @CsvBindByName(column = "Fixture Mount")
    @CsvBindByPosition(position =31)
    private String fixtureMount;

    @CsvBindByName(column = "Accessory Mount")
    @CsvBindByPosition(position =32)
    private String accessoryMount;

    @CsvBindByName(column = "Remote Control Type")
    @CsvBindByPosition(position =33)
    private String remoteControlType;

    @CsvBindByName(column = "AC Input Power")
    @CsvBindByPosition(position =34)
    private String acInputPower;

    @CsvBindByName(column = "DC Input Power")
    @CsvBindByPosition(position =35)
    private String dcInputPower;

    @CsvBindByName(column = "Power Source")
    @CsvBindByPosition(position =36)
    private String powerSource;

    @CsvBindByName(column = "Max Power Consumption")
    @CsvBindByPosition(position =37)
    private String maxPowerConsumption;

    @CsvBindByName(column = "Type")
    @CsvBindByPosition(position =38)
    private String type;

    @CsvBindByName(column = "Connector 1")
    @CsvBindByPosition(position =39)
    private String connector1;

    @CsvBindByName(column = "Connector 2")
    @CsvBindByPosition(position =40)
    private String connector2;

    @CsvBindByName(column = "Output 1")
    @CsvBindByPosition(position =41)
    private String output1;

    @CsvBindByName(column = "Output 2")
    @CsvBindByPosition(position =42)
    private String output2;

    @CsvBindByName(column = "Circuit Breaker")
    @CsvBindByPosition(position =43)
    private String circuitBreaker;

    @CsvBindByName(column = "Number of Conductors")
    @CsvBindByPosition(position =44)
    private String numberOfConductors;

    @CsvBindByName(column = "Material")
    @CsvBindByPosition(position =45)
    private String material;

    @CsvBindByName(column = "Wire Guage")
    @CsvBindByPosition(position =46)
    private String wireGuage;

    @CsvBindByName(column = "Certifications")
    @CsvBindByPosition(position =47)
    private String certifications;

    @CsvBindByName(column = "Light Reduction")
    @CsvBindByPosition(position =48)
    private String lightReduction;

    @CsvBindByName(column = "Shape")
    @CsvBindByPosition(position =49)
    private String shape;

    @CsvBindByName(column = "Ports")
    @CsvBindByPosition(position =50)
    private String ports;

    @CsvBindByName(column = "Channels")
    @CsvBindByPosition(position =51)
    private String channels;

    @CsvBindByName(column = "Wireless Range")
    @CsvBindByPosition(position =52)
    private String wirelessRange;

    @CsvBindByName(column = "Frequency")
    @CsvBindByPosition(position =53)
    private String frequency;

    @CsvBindByName(column = "Antenna Gain")
    @CsvBindByPosition(position =54)
    private String antennaGain;

    @CsvBindByName(column = "Compatibility")
    @CsvBindByPosition(position =55)
    private String compatibility;

    @CsvBindByName(column = "Cable Length")
    @CsvBindByPosition(position =56)
    private String cableLength;

    @CsvBindByName(column = "Capacity")
    @CsvBindByPosition(position =57)
    private String capacity;

    @CsvBindByName(column = "Key Features")
    @CsvBindByPosition(position = 58)
    private String keyFeatures;


}
