package com.wob.homework.repository;

import com.wob.homework.entity.MarketPlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceRepository extends JpaRepository<MarketPlaceEntity, Long> {
}
