package com.wob.homework.service.impl;

import com.wob.homework.dto.ListingDTO;
import com.wob.homework.entity.ListingEntity;
import com.wob.homework.model.ListingValidationResult;
import com.wob.homework.repository.ListingRepository;
import com.wob.homework.repository.MarketplaceRepository;
import com.wob.homework.service.ApiService;
import com.wob.homework.service.ListingService;
import com.wob.homework.service.ListingValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingServiceImpl.class);

    private final ApiService apiService;
    private final ListingValidatorService listingValidatorService;
    private final ListingRepository listingRepository;
    private final MarketplaceRepository marketplaceRepository;

    @Autowired
    public ListingServiceImpl(ApiService apiService, ListingValidatorService listingValidatorService, ListingRepository listingRepository, MarketplaceRepository marketplaceRepository) {
        this.apiService = apiService;
        this.listingValidatorService = listingValidatorService;
        this.listingRepository = listingRepository;
        this.marketplaceRepository = marketplaceRepository;
    }

    @Override
    public void fetchAndSaveListingData() {
        LOGGER.info("Listing fetching is in progress...");
        List<ListingDTO> listingList = apiService.getListingList();
        ListingValidationResult listingValidationResult = listingValidatorService.validateListings(listingList);
        if (!listingValidationResult.getInvalidListingData().isEmpty()) {
            //TODO create csv
        }

        ListingEntity listingEntity = mapDTOToEntity(listingValidationResult.getValidListingList().get(0));
        listingRepository.save(listingEntity);
        List<ListingEntity> all = listingRepository.findAll();
        String marketplaceName = all.get(0).getMarketplace().getMarketplaceName();

        int i = 0;


    }

    private ListingEntity mapDTOToEntity(ListingDTO listingDTO) {
        marketplaceRepository.getById(listingDTO.getMarketplace());
        return new ListingEntity(listingDTO.getId(), listingDTO.getTitle(), marketplaceRepository.getById(listingDTO.getMarketplace()));
    }

}
