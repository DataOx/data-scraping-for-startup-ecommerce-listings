package com.dataox.datascraper.decl.reportservice;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessoriesReport implements CsvBean {

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
    @CsvBindByPosition(position = 12)
    private String image2;

    @CsvBindByName(column = "Image 3")
    @CsvBindByPosition(position = 13)
    private String image3;

    @CsvBindByName(column = "Image 4")
    @CsvBindByPosition(position = 14)
    private String image4;

    @CsvBindByName(column = "Image 5")
    @CsvBindByPosition(position =15)
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

    @CsvBindByName(column = "Compatibility")
    @CsvBindByPosition(position = 21)
    private String compatibility;

    @CsvBindByName(column = "Voltage")
    @CsvBindByPosition(position = 22)
    private String voltage;

    @CsvBindByName(column = "Capacity")
    @CsvBindByPosition(position = 23)
    private String capacity;

    @CsvBindByName(column = "Battery Info")
    @CsvBindByPosition(position = 24)
    private String batteryInfo;

    @CsvBindByName(column = "Battery Material")
    @CsvBindByPosition(position = 25)
    private String batteryMaterial;

    @CsvBindByName(column = "Dimensions")
    @CsvBindByPosition(position = 26)
    private String dimensionsCopy = this.dimensions;

    @CsvBindByName(column = "Clamp Diameter")
    @CsvBindByPosition(position = 27)
    private String clampDiameter;

    @CsvBindByName(column = "Cable Length")
    @CsvBindByPosition(position = 28)
    private String cableLength;

    @CsvBindByName(column = "Transfer Speed")
    @CsvBindByPosition(position = 29)
    private String transferSpeed;

    @CsvBindByName(column = "Type")
    @CsvBindByPosition(position = 30)
    private String type;

    @CsvBindByName(column = "Sync Speed")
    @CsvBindByPosition(position = 31)
    private String syncSpeed;

    @CsvBindByName(column = "Transmission System")
    @CsvBindByPosition(position = 32)
    private String transmissionSystem;

    @CsvBindByName(column = "Transmission Range")
    @CsvBindByPosition(position = 33)
    private String transmissionRange;

    @CsvBindByName(column = "Flash Ratio Control")
    @CsvBindByPosition(position = 34)
    private String flashRatioControl;

    @CsvBindByName(column = "Flash Coverage")
    @CsvBindByPosition(position = 35)
    private String flashCoverage;

    @CsvBindByName(column = "Power Source")
    @CsvBindByPosition(position = 36)
    private String powerSource;

    @CsvBindByName(column = "Battery Life")
    @CsvBindByPosition(position = 37)
    private String batteryLife;

    @CsvBindByName(column = "Mounting Point")
    @CsvBindByPosition(position = 38)
    private String mountingPoint;

    @CsvBindByName(column = "Connection Type")
    @CsvBindByPosition(position = 39)
    private String connectionType;

    @CsvBindByName(column = "Storage Capacity")
    @CsvBindByPosition(position = 40)
    private String storageCapacity;

    @CsvBindByName(column = "Data Transfer")
    @CsvBindByPosition(position = 41)
    private String dataTransfer;

    @CsvBindByName(column = "Load Capacityr")
    @CsvBindByPosition(position = 42)
    private String loadCapacity;

    @CsvBindByName(column = "Voltage")
    @CsvBindByPosition(position = 43)
    private String voltageCopy = this.voltage;

    @CsvBindByName(column = "Mount Type")
    @CsvBindByPosition(position = 44)
    private String mountType;

    @CsvBindByName(column = "Size")
    @CsvBindByPosition(position = 45)
    private String size;

    @CsvBindByName(column = "Resolution")
    @CsvBindByPosition(position = 46)
    private String resolution;

    @CsvBindByName(column = "Aspect Ratio")
    @CsvBindByPosition(position = 47)
    private String aspectRatio;

    @CsvBindByName(column = "Input Connection Type")
    @CsvBindByPosition(position = 48)
    private String inputConnectionType;

    @CsvBindByName(column = "Output Connection Type")
    @CsvBindByPosition(position = 49)
    private String outputConnectionType;

    @CsvBindByName(column = "AudioIn")
    @CsvBindByPosition(position = 50)
    private String audioIn;

    @CsvBindByName(column = "AudioOut")
    @CsvBindByPosition(position = 51)
    private String audioOut;

    @CsvBindByName(column = "Key Features")
    @CsvBindByPosition(position = 52)
    private String keyFeatures;
}
