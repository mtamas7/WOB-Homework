package com.wob.homework.repository;

import com.wob.homework.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    LocationEntity getById(String uuid);
}
