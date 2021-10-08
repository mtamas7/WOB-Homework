package com.wob.homework.repository;

import com.wob.homework.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    /*@Modifying
    @Query(
            value = "truncate table locations",
            nativeQuery = true
    )
    void truncateLocationTable();*/

    LocationEntity getById(String uuid);
}
