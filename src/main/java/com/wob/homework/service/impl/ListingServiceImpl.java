package com.wob.homework.service.impl;

import com.wob.homework.dto.ListingDTO;
import com.wob.homework.entity.ListingEntity;
import com.wob.homework.model.ListingValidationResult;
import com.wob.homework.repository.ListingRepository;
import com.wob.homework.repository.ListingStatusRepository;
import com.wob.homework.repository.LocationRepository;
import com.wob.homework.repository.MarketplaceRepository;
import com.wob.homework.service.ApiService;
import com.wob.homework.service.ListingService;
import com.wob.homework.service.ListingValidatorService;
import com.wob.homework.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingServiceImpl.class);

    private final ApiService apiService;
    private final ListingValidatorService listingValidatorService;
    private final ReportService reportService;
    private final ListingRepository listingRepository;
    private final ListingStatusRepository listingStatusRepository;
    private final MarketplaceRepository marketplaceRepository;
    private final LocationRepository locationRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/y");

    @Autowired
    public ListingServiceImpl(ApiService apiService, ListingValidatorService listingValidatorService, ReportService reportService, ListingRepository listingRepository, ListingStatusRepository listingStatusRepository, MarketplaceRepository marketplaceRepository, LocationRepository locationRepository) {
        this.apiService = apiService;
        this.listingValidatorService = listingValidatorService;
        this.reportService = reportService;
        this.listingRepository = listingRepository;
        this.listingStatusRepository = listingStatusRepository;
        this.marketplaceRepository = marketplaceRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public void fetchAndSaveListingData() {
        LOGGER.info("Listing fetching is in progress...");
        List<ListingDTO> listingList = apiService.getListingList();
        ListingValidationResult listingValidationResult = listingValidatorService.validateListings(listingList);

        if (!listingValidationResult.getInvalidListingData().isEmpty()) {
            reportService.createLogFileForInvalidData(listingValidationResult.getInvalidListingData());
        }
        if (!listingValidationResult.getValidListingList().isEmpty()) {
            List<ListingEntity> entities = listingValidationResult.getValidListingList().stream().map(this::mapDTOToEntity).collect(Collectors.toList());
            listingRepository.saveAll(entities);

            List<ListingEntity> all = listingRepository.findAll();
            String marketplaceName = all.get(0).getMarketplace().getMarketplaceName();
        }
    }

    private ListingEntity mapDTOToEntity(ListingDTO listingDTO) {

        marketplaceRepository.getById(listingDTO.getMarketplace());
        
        return new ListingEntity(listingDTO.getId(),
                listingDTO.getTitle(),
                listingDTO.getDescription(),
                locationRepository.getById(listingDTO.getLocationId()),
                listingDTO.getListingPrice(), listingDTO.getCurrency(),
                listingDTO.getQuantity(),
                listingStatusRepository.getById(listingDTO.getListingStatus()),
                marketplaceRepository.getById(listingDTO.getMarketplace()),
                listingDTO.getUploadTime() != null ? LocalDate.parse(listingDTO.getUploadTime(), formatter) : LocalDate.now(),
                listingDTO.getOwnerEmailAddress());
    }

}
