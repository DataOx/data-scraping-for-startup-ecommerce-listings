package com.dataox.datascraper.decl.reportservice;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LensesReport implements CsvBean {

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

    @CsvBindByName(column = "Lens Type")
    @CsvBindByPosition(position = 21)
    private String lensType;

    @CsvBindByName(column = "Mount")
    @CsvBindByPosition(position = 22)
    private String mount;

    @CsvBindByName(column = "Aperture")
    @CsvBindByPosition(position = 23)
    private String aperture;

    @CsvBindByName(column = "Focal Length")
    @CsvBindByPosition(position = 24)
    private String focalLength;

    @CsvBindByName(column = "Focus Type")
    @CsvBindByPosition(position = 25)
    private String focusType;

    @CsvBindByName(column = "Filter Size")
    @CsvBindByPosition(position = 26)
    private String filterSize;

    @CsvBindByName(column = "Diameter")
    @CsvBindByPosition(position = 27)
    private String diameter;

    @CsvBindByName(column = "Angle of View")
    @CsvBindByPosition(position = 28)
    private String angleOfView;

    @CsvBindByName(column = "Compatibility")
    @CsvBindByPosition(position = 29)
    private String compatibility;

    @CsvBindByName(column = "Zoom Method")
    @CsvBindByPosition(position = 30)
    private String zoomMethod;

    @CsvBindByName(column = "Hood Included")
    @CsvBindByPosition(position = 31)
    private String hoodIncluded;

    @CsvBindByName(column = "Image Stabilization")
    @CsvBindByPosition(position = 32)
    private String imageStabilization;

    @CsvBindByName(column = "Low Dispersion Elements")
    @CsvBindByPosition(position = 33)
    private String lowDispersionElements;

    @CsvBindByName(column = "Max Aperture")
    @CsvBindByPosition(position = 34)
    private String maxAperture;

    @CsvBindByName(column = "Minimum Aperture")
    @CsvBindByPosition(position = 35)
    private String minimumAperture;

    @CsvBindByName(column = "Minimum Focusing Distance")
    @CsvBindByPosition(position = 36)
    private String minimumFocusingDistance;

    @CsvBindByName(column = "Key Features")
    @CsvBindByPosition(position = 37)
    private String keyFeatures;
}
