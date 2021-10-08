package com.wob.homework.repository;

import com.wob.homework.entity.ListingStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingStatusRepository extends JpaRepository<ListingStatusEntity, Long> {
 /*   @Modifying
    @Query(
            value = "truncate table listing_statuses",
            nativeQuery = true
    )
    void truncateListingStatusesTable();*/
}
