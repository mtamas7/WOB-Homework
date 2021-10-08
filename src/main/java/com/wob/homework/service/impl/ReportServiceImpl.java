package com.wob.homework.service.impl;

import com.wob.homework.model.InvalidListingData;
import com.wob.homework.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    private static final String LOG_FILE_NAME = "importLog.csv";

    @Override
    public void createLogFileForInvalidData(List<InvalidListingData> invalidListingList) {
        LOGGER.info("Processing invalid listing data...");
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE_NAME);
            PrintWriter printWriter = new PrintWriter(fileWriter, true);
            for (InvalidListingData invalidListingData : invalidListingList) {
                printWriter.print(getRowFromInvalidData(invalidListingData));
            }
            printWriter.close();

        } catch (IOException e) {
            LOGGER.error("Something went wrong while processing invalid listings data... ");
        }
    }

    private String getRowFromInvalidData(InvalidListingData invalidListingData) {
        return String.format("%s,%s,%s\n", invalidListingData.getId(), invalidListingData.getMarketplaceName(), String.join(",", invalidListingData.getInvalidFieldNames()));
    }
}
