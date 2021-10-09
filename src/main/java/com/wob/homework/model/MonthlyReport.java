package com.wob.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReport {
    String date;
    Long listingPerMonth;
    Double totalPriceMonthly;
    Double avgListingPriceMonthly;
    String marketplaceName;
}
