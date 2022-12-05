package com.dataox.datascraper.scraper.parser.attributes;

import com.dataox.datascraper.domain.ScraperTask;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParserResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String requestType;
    private String error;
    @Column(name = "created_at")
    private Instant createdAt;
    @JoinColumn(name = "scraper_task_id")
    @ManyToOne(optional = false)
    private ScraperTask task;
    private String manufacturer;
    private String manufacturerPart;
    @Column(columnDefinition = "TEXT")
    private String image1;
    @Column(columnDefinition = "TEXT")
    private String image2;
    @Column(columnDefinition = "TEXT")
    private String image3;
    @Column(columnDefinition = "TEXT")
    private String image4;
    @Column(columnDefinition = "TEXT")
    private String image5;
    @Column(columnDefinition = "TEXT")
    private String image6;
    @Column(columnDefinition = "TEXT")
    private String image7;
    @Column(columnDefinition = "TEXT")
    private String image8;
    @Column(columnDefinition = "TEXT")
    private String image9;
    @Column(columnDefinition = "TEXT")
    private String image10;
    private String price;
    @Column(columnDefinition = "TEXT")
    private String weight;
    @Column(columnDefinition = "TEXT")
    private String dimensions;
    @Column(columnDefinition = "TEXT")
    private String compatibility;
    private String voltage;
    private String capacity;
    private String batteryInfo;
    private String batteryMaterial;
    private String clampDiameter;
    private String cableLength;
    private String transferSpeed;
    private String type;
    private String syncSpeed;
    private String transmissionSystem;
    private String transmissionRange;
    private String flashRatioControl;
    private String flashCoverage;
    private String powerSource;
    private String batteryLife;
    private String mountingPoint;
    private String connectionType;
    private String storageCapacity;
    private String dataTransfer;
    private String loadCapacity;
    private String mountType;
    private String size;
    private String resolution;
    private String aspectRatio;
    private String inputConnectionType;
    private String outputConnectionType;
    private String audioIn;
    private String audioOut;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String keyFeatures;
    @Column(columnDefinition = "TEXT")
    private String mount;
    @Column(columnDefinition = "TEXT")
    private String sensorType;
    private String sensorSize;
    @Column(columnDefinition = "TEXT")
    private String mediaType;
    @Column(columnDefinition = "TEXT")
    private String ISORange;
    @Column(columnDefinition = "TEXT")
    private String recordingResolution;
    @Column(columnDefinition = "TEXT")
    private String recordingFormat;
    @Column(columnDefinition = "TEXT")
    private String videoOutputType;
    private String headphoneOut;
    @Column(columnDefinition = "TEXT")
    private String audioIO;
    @Column(columnDefinition = "TEXT")
    private String monitorSize;
    @Column(columnDefinition = "TEXT")
    private String monitorType;
    @Column(columnDefinition = "TEXT")
    private String powerInput;
    @Column(columnDefinition = "TEXT")
    private String colorGammaModes;
    @Column(columnDefinition = "TEXT")
    private String recorderChannelCount;
    @Column(columnDefinition = "TEXT")
    private String maxRecordTime;
    private String sensorSensitivity;
    private String sensorSignalToNoiseRatio;
    private String sensorDynamicRange;
    private String sensorDimensions;
    private String stabilization;
    @Column(columnDefinition = "TEXT")
    private String timecode;
    @Column(columnDefinition = "TEXT")
    private String viewfinderFeatures;
    @Column(columnDefinition = "TEXT")
    private String wirelessFeatures;
    @Column(columnDefinition = "TEXT")
    private String shutterSpeedRange;
    private String aperture;
    private String maxAperture;
    private String minimumAperture;
    private String focalLength;
    private String diameter;
    private String zoomMethod;
    private String lensType;
    private String focusType;
    private String lowDispersionElements;
    private String filterSize;
    private String angleOfView;
    private String imageStabilization;
    private String minimumFocusingDistance;
    private String model;
    @Column(columnDefinition = "TEXT")
    private String photometrics;
    private String colorTemperature;
    private String colorAccuracyStandard;
    private String coolingSystem;
    private String dimming;
    private String display;
    private String housingMaterial;
    private String beamAngle;
    private String powerInputConnection;
    private String yokeMount;
    private String fixtureMount;
    private String accessoryMount;
    private String remoteControlType;
    private String ACInputPower;
    private String DCInputPower;
    private String maxPowerConsumption;
    private String connector1;
    private String connector2;
    private String output1;
    private String output2;
    private String circuitBreaker;
    private String numberOfCConductors;
    private String material;
    private String wireGuage;
    private String certifications;
    private String lightReduction;
    private String shape;
    private String ports;
    private String channels;
    private String wirelessRange;
    private String frequency;
    private String antennaGain;

}