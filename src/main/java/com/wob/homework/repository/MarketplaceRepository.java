package com.wob.homework.repository;

import com.wob.homework.entity.MarketplaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceRepository extends JpaRepository<MarketplaceEntity, Long> {
    /*@Modifying
    @Query(
            value = "truncate table marketplaces",
            nativeQuery = true
    )
    void truncateListingsTable();*/
}
