package com.wob.homework.service.impl;

import com.wob.homework.dto.ListingDTO;
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

    @Autowired
    public ListingServiceImpl(ApiService apiService, ListingValidatorService listingValidatorService) {
        this.apiService = apiService;
        this.listingValidatorService = listingValidatorService;
    }

    @Override
    public void fetchAndSaveListingData() {
        LOGGER.info("Listing fetching is in progress...");

        List<ListingDTO> listingList = apiService.getListingList();
//        ListingValidationResult listingValidationResult = listingValidatorService.validateListings(listingList);
    }

}
