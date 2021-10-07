package com.wob.homework.service.impl;

import com.wob.homework.dto.LocationDTO;
import com.wob.homework.entity.LocationEntity;
import com.wob.homework.repository.LocationRepository;
import com.wob.homework.service.ApiService;
import com.wob.homework.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

    private final ApiService apiService;
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(ApiService apiService, LocationRepository locationRepository) {
        this.apiService = apiService;
        this.locationRepository = locationRepository;
    }

    @Override
    public void fetchAndSaveLocationData() {
        LOGGER.info("Listing location fetching is in progress...");
        List<LocationDTO> locationList = apiService.getLocationList();
        List<LocationEntity> locationEntities = locationList.stream()
                .map(this::mapLocationDTO)
                .collect(Collectors.toList());
        if (!locationEntities.isEmpty()) {
            LOGGER.info("Location data fetch is done, save entities into the database");
            locationRepository.saveAll(locationEntities);
            LOGGER.info("Location data save finished");
        } else {
            LOGGER.info("Empty response, no saving to the database");
        }
    }

    private LocationEntity mapLocationDTO(LocationDTO locationDTO) {
        return new LocationEntity(UUID.fromString(locationDTO.getId()),
                locationDTO.getManagerName(),
                locationDTO.getPhone(),
                locationDTO.getAddressPrimary(),
                locationDTO.getAddressSecondary(),
                locationDTO.getCountry(),
                locationDTO.getTown(),
                locationDTO.getPostalCode());
    }
}
