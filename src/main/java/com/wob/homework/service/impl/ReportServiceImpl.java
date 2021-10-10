package com.wob.homework.service.impl;

import com.google.gson.GsonBuilder;
import com.wob.homework.model.InvalidListingData;
import com.wob.homework.model.MonthlyReport;
import com.wob.homework.model.MonthlyStatistics;
import com.wob.homework.model.Report;
import com.wob.homework.model.TotalStatistics;
import com.wob.homework.repository.ListingRepository;
import com.wob.homework.service.ReportService;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    private static final String LOG_FILE_NAME = "importLog.csv";
    private static final String REPORT_FILE_NAME = "report.json";
    private static final String AMAZON = "AMAZON";
    private static final String EBAY = "EBAY";

    private final ListingRepository listingRepository;

    @Value("${ftp.url}")
    private String ftpUrl;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Autowired
    public ReportServiceImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

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
            LOGGER.info("Processing invalid listing data finished");
        } catch (IOException e) {
            LOGGER.error("Something went wrong while processing invalid listings data... ");
        }
    }

    @Override
    public void createAndUploadReport() {
        LOGGER.info("Create report from listing data...");
        try {
            FileWriter writer = new FileWriter(REPORT_FILE_NAME);
            new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create()
                    .toJson(createReport(), writer);
            writer.flush();
            writer.close();
            uploadReportFile();
            LOGGER.info("Creating report has finished");
        } catch (IOException e) {
            LOGGER.error("Something went wrong while creating report...");
        }
    }

    private Report createReport() {

        TotalStatistics amazonStatistics = listingRepository.getTotalStatisticsByMarketplaceName(AMAZON);
        TotalStatistics ebayStatistics = listingRepository.getTotalStatisticsByMarketplaceName(EBAY);
        List<MonthlyStatistics> amazonMonthlyStatistics = listingRepository.getMonthlyStatisticsByMarketplaceName(AMAZON);
        List<MonthlyStatistics> ebayMonthlyStatistics = listingRepository.getMonthlyStatisticsByMarketplaceName(EBAY);
        amazonMonthlyStatistics.addAll(ebayMonthlyStatistics);

        List<MonthlyReport> monthlyReportList = amazonMonthlyStatistics.stream().map(this::mapMonthlyStatistics).collect(Collectors.toList());

        Report report = new Report();
        report.setTotalListingCount(listingRepository.getTotalListingCount());

        report.setTotalEbayListingCount(ebayStatistics.getTotalCount());
        report.setTotalEbayListingPrice(ebayStatistics.getTotalPrice());
        report.setAverageEbayListingPrice(ebayStatistics.getAveragePrice());

        report.setTotalAmazonListingCount(amazonStatistics.getTotalCount());
        report.setTotalAmazonListingPrice(amazonStatistics.getTotalPrice());
        report.setAverageAmazonListingPrice(amazonStatistics.getAveragePrice());

        report.setBestListerEmailAddress(listingRepository.getBestListerEmailAddress());

        report.setMonthlyReports(monthlyReportList);

        return report;
    }

    private MonthlyReport mapMonthlyStatistics(MonthlyStatistics monthlyStatistics) {
        return new MonthlyReport(monthlyStatistics.getDate(),
                monthlyStatistics.getListingPerMonth(),
                monthlyStatistics.getTotalPriceMonthly(),
                monthlyStatistics.getAvgListingPriceMonthly(),
                monthlyStatistics.getMarketplaceName());
    }

    private String getRowFromInvalidData(InvalidListingData invalidListingData) {
        return String.format("%s,%s,%s\n", invalidListingData.getId(), invalidListingData.getMarketplaceName(), String.join(",", invalidListingData.getInvalidFieldNames()));
    }

    private void uploadReportFile() {
        try {
            File file = ResourceUtils.getFile(REPORT_FILE_NAME);
            FTPSClient client = new FTPSClient();
            client.connect(ftpUrl);
            boolean login = client.login(ftpUsername, ftpPassword);
            if (login) {
                LOGGER.info("Successful login to ftp");
                client.storeFile(REPORT_FILE_NAME, new FileInputStream(file));
                client.logout();
                LOGGER.info("File uploading has been completed");
            }

        } catch (IOException e) {
            LOGGER.error("Something went wrong while uploading the report...");
        }
    }
}
