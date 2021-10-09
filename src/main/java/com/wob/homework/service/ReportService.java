package com.wob.homework.service;

import com.wob.homework.model.InvalidListingData;

import java.util.List;

public interface ReportService {

    /**
     * Create a csv file from the data of the invalid listings
     */
    void createLogFileForInvalidData(List<InvalidListingData> invalidListingData);

    /**
     * Create and upload the report json from the listing data to the configured ftp server
     */
    void createAndUploadReport();
}
