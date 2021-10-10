package com.wob.homework.model;

public interface MonthlyStatistics {
    String getDate();

    Long getListingPerMonth();

    Double getTotalPriceMonthly();

    Double getAvgListingPriceMonthly();

    String getMarketplaceName();


}
