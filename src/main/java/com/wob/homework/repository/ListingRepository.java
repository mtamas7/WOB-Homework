package com.wob.homework.repository;

import com.wob.homework.entity.ListingEntity;
import com.wob.homework.model.MonthlyStatistics;
import com.wob.homework.model.TotalStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListingRepository extends JpaRepository<ListingEntity, Long> {

    @Query(value = "select count(id) from listings;", nativeQuery = true)
    Long getTotalListingCount();

    @Query(value = "select marketplaces.marketplace_name, count(listings.ID)  as totalCount, sum(listings.price) as totalPrice, avg(listings.price) as averagePrice from listings join marketplaces on marketplaces.ID = listings.marketplace_id where marketplaces.MARKETPLACE_NAME = ?1", nativeQuery = true)
    TotalStatistics getTotalStatisticsByMarketplaceName(String marketplaceName);

    @Query(value = "select owner_email_address from listings group by owner_email_address order by count(owner_email_address) desc limit 1", nativeQuery = true)
    String getBestListerEmailAddress();

    @Query(value = "select DATE_FORMAT(listings.UPLOAD_TIME, '%Y-%m') as date, count(*) as listingPerMonth, sum(listings.price) as totalPriceMonthly, avg(listings.price) as avgListingPriceMonthly, marketplaces.marketplace_name as marketplaceName from listings join marketplaces on marketplaces.ID = listings.marketplace_id where marketplaces.marketplace_name = ?1 group by DATE_FORMAT(listings.upload_time, '%Y-%m') ORDER BY MIN(listings.upload_time);\n", nativeQuery = true)
    List<MonthlyStatistics> getMonthlyStatisticsByMarketplaceName(String marketplaceName);
}
