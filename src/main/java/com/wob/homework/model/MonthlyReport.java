package com.wob.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReport {
    private String date;
    private Long listingPerMonth;
    private Double totalPriceMonthly;
    private Double avgListingPriceMonthly;
    private String marketplaceName;
}
