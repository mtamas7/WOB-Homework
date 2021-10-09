package com.wob.homework.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private long totalListingCount;

    private long totalEbayListingCount;
    private double totalEbayListingPrice;
    private double averageEbayListingPrice;

    private long totalAmazonListingCount;
    private double totalAmazonListingPrice;
    private double averageAmazonListingPrice;

    private String bestListerEmailAddress;

    private List<MonthlyReport> monthlyReports;
}
