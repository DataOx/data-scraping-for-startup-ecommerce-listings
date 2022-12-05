package com.dataox.datascraper.impl.taskservice;

import com.dataox.datascraper.decl.FlowStatus;
import com.dataox.datascraper.decl.ScraperType;
import com.dataox.datascraper.decl.SourceType;
import com.dataox.datascraper.decl.taskservice.ScraperTaskService;
import com.dataox.datascraper.domain.ScraperTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.*;

import static com.dataox.datascraper.decl.FlowStatus.PENDING;
import static com.dataox.datascraper.decl.ScraperType.UNKNOWN;

@Slf4j
@Service
@RequiredArgsConstructor
class ScraperTaskServiceImpl implements ScraperTaskService {

    private final ScraperTaskDataServiceDb scraperTaskDataService;

    private final DataFormatter formatter = new DataFormatter(Locale.US);

    public List<ScraperTask> getAllByStatus(FlowStatus status) {
        return scraperTaskDataService.getAllByStatus(status);
    }

    public ScraperTask updateFlowStatus(String address, FlowStatus newFlowStatus) {
        ScraperTask task = scraperTaskDataService.findByAddress(address).orElseThrow();
        task.setExecutedAt(Instant.now());
        task.setFlowStatus(newFlowStatus);
        return scraperTaskDataService.upsert(task);
    }

    @Override
    public void createFromXLSXFile(MultipartFile file) throws IOException {
        List<ScraperTask> scraperTasks = createScraperTasks(file);
        saveOrUpdateScraperTasks(scraperTasks);
    }

    private void saveOrUpdateScraperTasks(List<ScraperTask> scraperTasks) {
        for (ScraperTask task : scraperTasks) {
            Optional<ScraperTask> scraperTask = scraperTaskDataService
                    .findByAddress(task.getSourceAddress());

            if (scraperTask.isEmpty()) {
                scraperTaskDataService.upsert(task);
            } else {
                ScraperTask taskFromDb = scraperTask.get();
                BeanUtils.copyProperties(task, taskFromDb, "id", "flowStatus");
                scraperTaskDataService.upsert(taskFromDb);
            }
        }
    }

    private List<ScraperTask> createScraperTasks(MultipartFile file) throws IOException {
        List<ScraperTask> scraperTasks = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int currentRow = 1;
            while ((currentRow - 1) != sheet.getLastRowNum()) {
                scraperTasks.add(createScraperTask(sheet.getRow(currentRow++)));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return scraperTasks;
    }

    private ScraperTask createScraperTask(Row row) {
        String sourceType = getStringCellValue(row, 0);
        String title = getStringCellValue(row, 1);
        String bin = getStringCellValue(row, 2);
        String brand = getStringCellValue(row, 3);
        String price = getStringCellValue(row, 4);
        String description = getStringCellValue(row, 5);
        String website = prepareString(getStringCellValue(row, 6));

        ScraperTask task = new ScraperTask();
        task.setScraperType(parseType(website));
        task.setBin(bin);
        task.setTitle(title);
        task.setBrand(brand);
        task.setSourceAddress(website);
        task.setSourceType(parseSourceType(sourceType));
        task.setDescription(description);
        task.setPrice(price);
        task.setFlowStatus(PENDING);
        task.setCreatedAt(Instant.now());
        return task;
    }

    private String prepareString(String stringCellValue) {
        return StringUtils.replace(stringCellValue, "␀", "_x2400_");
    }

    private String getStringCellValue(Row row, int i) {
        return formatter.formatCellValue(row.getCell(i));
    }

    private SourceType parseSourceType(String sourceType) {
        return Arrays.stream(SourceType.values())
                .filter(type -> StringUtils.equalsIgnoreCase(sourceType, type.name()))
                .findFirst()
                .orElse(SourceType.UNKNOWN);
    }

    private ScraperType parseType(String address) {
        return Arrays.stream(ScraperType.values())
                .filter(st -> StringUtils.containsIgnoreCase(address, st.getAddressPart()))
                .findFirst()
                .orElse(UNKNOWN);
    }

}
