package com.wob.homework.service.impl;

import com.wob.homework.dto.ListingStatusDTO;
import com.wob.homework.entity.ListingStatusEntity;
import com.wob.homework.repository.ListingStatusRepository;
import com.wob.homework.service.ApiService;
import com.wob.homework.service.ListingStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingStatusServiceImpl implements ListingStatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingStatusServiceImpl.class);

    private final ApiService apiService;
    private final ListingStatusRepository listingStatusRepository;

    @Autowired
    public ListingStatusServiceImpl(ApiService apiService, ListingStatusRepository listingStatusRepository) {
        this.apiService = apiService;
        this.listingStatusRepository = listingStatusRepository;
    }

    @Override
    @Transactional
    public void fetchAndSaveListingStatusData() {
        LOGGER.info("Listing status data fetching is in progress...");
        List<ListingStatusDTO> listingStatusList = apiService.getListingStatusList();
        List<ListingStatusEntity> listingStatusEntities = listingStatusList.stream()
                .map(this::mapListingStatusDTO)
                .collect(Collectors.toList());
        if (!listingStatusEntities.isEmpty()) {
            LOGGER.info("Listing status data fetch is done, save entities into the database");
            listingStatusRepository.saveAll(listingStatusEntities);
            LOGGER.info("Listing status data save finished");
        } else {
            LOGGER.info("Empty response, no saving to the database");
        }
    }

    private ListingStatusEntity mapListingStatusDTO(ListingStatusDTO listingStatusDTO) {
        return new ListingStatusEntity(listingStatusDTO.getId(), listingStatusDTO.getStatusName());
    }
}
