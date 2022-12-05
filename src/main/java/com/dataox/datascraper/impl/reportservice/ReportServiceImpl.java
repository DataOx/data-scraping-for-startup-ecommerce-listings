package com.dataox.datascraper.impl.reportservice;

import com.dataox.datascraper.decl.SourceType;
import com.dataox.datascraper.decl.reportservice.*;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class ReportServiceImpl implements ReportService {

    private static final String DOC_NAME_PATTERN = "%s_%s.csv";

    @Value("${app.report.path}")
    private String path;

    private final ReportMapper mapper = Mappers.getMapper(ReportMapper.class);

    @Override
    public void createReport(List<ReportRequest> reportRequestList) {

        for (SourceType sourceType : SourceType.values()) {
            List<ReportRequest> reportsByType = reportRequestList.stream()
                    .filter(r -> sourceType.equals(r.getSourceType()))
                    .collect(Collectors.toList());

            try {
                List<CsvBean> csvBeans = getCsvBeanList(sourceType, reportsByType);
                CustomMappingStrategy<CsvBean> mappingStrategy = getMappingStrategy(sourceType);

                writeCsvFromBean(getPath(sourceType), csvBeans, mappingStrategy);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private List<CsvBean> getCsvBeanList(SourceType sourceType, List<ReportRequest> reportsByType) {
        List<CsvBean> csvBeans = new ArrayList<>();
        if (sourceType == SourceType.CAMERA) {
            csvBeans = reportsByType.stream()
                    .map(mapper::mapToCameraReport)
                    .collect(Collectors.toList());
        } else if (sourceType == SourceType.LIGHTING) {
            csvBeans = reportsByType.stream()
                    .map(mapper::mapToLightingReport)
                    .collect(Collectors.toList());
        } else if (sourceType == SourceType.ACCESSORIES) {
            csvBeans = reportsByType.stream()
                    .map(mapper::mapToAccessoriesReport)
                    .collect(Collectors.toList());
        } else if (sourceType == SourceType.LENSES) {
            csvBeans = reportsByType.stream()
                    .map(mapper::mapToLensesReport)
                    .collect(Collectors.toList());
        }
        return csvBeans;
    }

    private CustomMappingStrategy<CsvBean> getMappingStrategy(SourceType sourceType) {
        CustomMappingStrategy<CsvBean> mappingStrategy = new CustomMappingStrategy<>();

        switch (sourceType) {
            case CAMERA:
                mappingStrategy.setType(CameraReport.class);
                break;
            case LENSES:
                mappingStrategy.setType(LensesReport.class);
                break;
            case ACCESSORIES:
                mappingStrategy.setType(AccessoriesReport.class);
                break;
            case LIGHTING:
                mappingStrategy.setType(LightingReport.class);
                break;
        }

        return mappingStrategy;
    }

    private Path getPath(SourceType sourceType) {
        return Path.of(path + String.format(DOC_NAME_PATTERN, sourceType.name(), LocalDate.now().toString()));
    }

    public void writeCsvFromBean(Path path, List<CsvBean> data, CustomMappingStrategy<CsvBean> mappingStrategy) throws Exception {

        try (Writer writer  = new FileWriter(path.toString())) {
            StatefulBeanToCsv<CsvBean> sbc = new StatefulBeanToCsvBuilder<CsvBean>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withMappingStrategy(mappingStrategy)
                    .build();

            sbc.write(data);
            log.info("created report {}", path);
        }
    }

    @Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
    interface ReportMapper {

        @Mapping(ignore = true, target = "recordingResolutionCopy")
        @Mapping(ignore = true, target = "recordingFormatCopy")
        @Mapping(ignore = true, target = "mediaTypeCopy")
        @Mapping(ignore = true, target = "descriptionCopy")
        CameraReport mapToCameraReport(ReportRequest source);

        @Mapping(ignore = true, target = "descriptionCopy")
        LensesReport mapToLensesReport(ReportRequest source);

        @Mapping(ignore = true, target = "voltageCopy")
        @Mapping(ignore = true, target = "dimensionsCopy")
        AccessoriesReport mapToAccessoriesReport(ReportRequest source);

        LightingReport mapToLightingReport(ReportRequest source);
    }

    class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
        @Override
        public String[] generateHeader() {
            final int numColumns = findMaxFieldIndex();
            if (!isAnnotationDriven() || numColumns == -1) {
                return super.generateHeader();
            }

            header = new String[numColumns + 1];

            for (int i = 0; i <= numColumns; i++) {
                String columnHeaderName = extractHeaderName(findField(i));
                header[i] = columnHeaderName;
            }
            return header;
        }

        private String extractHeaderName(final BeanField beanField) {
            if (beanField == null || beanField.getField() == null || beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
                return StringUtils.EMPTY;
            }

            final CsvBindByName bindByNameAnnotation = beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class)[0];
            return bindByNameAnnotation.column();
        }
    }
}
