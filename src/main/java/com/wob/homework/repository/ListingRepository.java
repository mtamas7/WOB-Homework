package com.wob.homework.repository;

import com.wob.homework.entity.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<ListingEntity, Long> {

}
