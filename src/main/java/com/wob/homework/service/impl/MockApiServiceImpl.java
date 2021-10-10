package com.wob.homework.service.impl;

import com.google.gson.Gson;
import com.wob.homework.dto.ListingDTO;
import com.wob.homework.dto.ListingStatusDTO;
import com.wob.homework.dto.LocationDTO;
import com.wob.homework.dto.MarketplaceDTO;
import com.wob.homework.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Profile("mock")
public class MockApiServiceImpl implements ApiService {
    Logger LOGGER = LoggerFactory.getLogger(MockApiServiceImpl.class);

    private final Gson gson;

    public MockApiServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public List<MarketplaceDTO> getMarketplaceList() {
        LOGGER.info("Reading mock marketplace data from file");
        String responseJSON = "";
        try {
            responseJSON = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:marketplace.json").toPath()));
        } catch (IOException e) {
            LOGGER.error("Error whileReading mock data from file");
        }
        MarketplaceDTO[] marketplaceDTOArray = gson.fromJson(responseJSON, MarketplaceDTO[].class);
        LOGGER.info("Reading mock marketplace data from file finished");
        return marketplaceDTOArray.length != 0 ? Arrays.asList(marketplaceDTOArray) : Collections.emptyList();
    }

    @Override
    public List<ListingStatusDTO> getListingStatusList() {
        LOGGER.info("Reading mock listing status data from file");
        String responseJSON = "";
        try {
            responseJSON = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:listingstatus.json").toPath()));
        } catch (IOException e) {
            LOGGER.error("Error whileReading mock data from file");
        }
        ListingStatusDTO[] listingStatusDTOArray = gson.fromJson(responseJSON, ListingStatusDTO[].class);
        LOGGER.info("Reading mock listing status data from file finished");
        return listingStatusDTOArray.length != 0 ? Arrays.asList(listingStatusDTOArray) : Collections.emptyList();
    }

    @Override
    public List<LocationDTO> getLocationList() {
        LOGGER.info("Reading mock location data from file");
        String responseJSON = "";
        try {
            responseJSON = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:locationdata.json").toPath()));
        } catch (IOException e) {
            LOGGER.error("Error whileReading mock data from file");
        }
        LocationDTO[] locationDTOArray = gson.fromJson(responseJSON, LocationDTO[].class);
        LOGGER.info("Reading mock location data from file finished");
        return locationDTOArray.length != 0 ? Arrays.asList(locationDTOArray) : Collections.emptyList();
    }

    @Override
    public List<ListingDTO> getListingList() {
        LOGGER.info("Reading mock listing data from file");
        String responseJSON = "";
        try {
            responseJSON = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:listing.json").toPath()));
        } catch (IOException e) {
            LOGGER.error("Error whileReading mock data from file");
        }
        ListingDTO[] listingDTOArray = gson.fromJson(responseJSON, ListingDTO[].class);
        LOGGER.info("Reading mock listing data from file finished");
        return listingDTOArray.length != 0 ? Arrays.asList(listingDTOArray) : Collections.emptyList();
    }
}
