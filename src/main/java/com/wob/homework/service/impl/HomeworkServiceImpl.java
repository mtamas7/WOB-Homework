package com.wob.homework.service.impl;

import com.wob.homework.service.HomeworkService;
import com.wob.homework.service.ListingStatusService;
import com.wob.homework.service.MarketplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HomeworkServiceImpl implements HomeworkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeworkServiceImpl.class);

    private final MarketplaceService marketplaceService;
    private final ListingStatusService listingStatusService;

    @Autowired
    public HomeworkServiceImpl(MarketplaceService marketplaceService, ListingStatusService listingStatusService) {
        this.marketplaceService = marketplaceService;
        this.listingStatusService = listingStatusService;
    }

    @Override
    public void fetchDataAndCreateReport() {
        LOGGER.info("Application started, fetch and process data from API is in progress, please wait... ");
//        marketplaceService.fetchAndSaveMarketplaceData();
        listingStatusService.fetchAndSaveListingStatusData();
    }
}
