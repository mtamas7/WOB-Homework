package com.wob.homework.service.impl;

import com.google.gson.Gson;
import com.wob.homework.dto.ListingDTO;
import com.wob.homework.dto.ListingStatusDTO;
import com.wob.homework.dto.LocationDTO;
import com.wob.homework.dto.MarketplaceDTO;
import com.wob.homework.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Value("${base.url}")
    private String baseUrl;

    @Value("${marketplace.url}")
    private String marketplaceUrl;

    @Value("${listingStatus.url}")
    private String listingStatusUrl;

    @Value("${location.url}")
    private String locationUrl;

    @Value("${listing.url}")
    private String listingUrl;

    @Value("${apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private final Gson gson;

    @Autowired
    public ApiServiceImpl(RestTemplate restTemplate, Gson gson) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    //todo create a mock profile to testing

    @Override
    public List<MarketplaceDTO> getMarketplaceList() {
        LOGGER.info("Fetching marketplace data from API");
//        String responseJSON = getResponseJSON(marketplaceUrl);
        String responseJSON = "";
        try {
            responseJSON = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:marketplace.json").toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MarketplaceDTO[] marketplaceDTOArray = gson.fromJson(responseJSON, MarketplaceDTO[].class);
        LOGGER.info("Fetching marketplace data from API finished");
        return marketplaceDTOArray.length != 0 ? Arrays.asList(marketplaceDTOArray) : Collections.emptyList();
    }

    @Override
    public List<ListingStatusDTO> getListingStatusList() {
        LOGGER.info("Fetching listing status data from API");
//        String responseJSON = getResponseJSON(listingStatusUrl);
        String responseJSON = "";
        try {
            responseJSON = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:listingstatus.json").toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListingStatusDTO[] listingStatusDTOArray = gson.fromJson(responseJSON, ListingStatusDTO[].class);
        LOGGER.info("Fetching listing status data from API finished");
        return listingStatusDTOArray.length != 0 ? Arrays.asList(listingStatusDTOArray) : Collections.emptyList();
    }

    @Override
    public List<LocationDTO> getLocationList() {
        LOGGER.info("Fetching location data from API");
//        String responseJSON = getResponseJSON(locationUrl);
        String responseJSON = "";
        try {
            responseJSON = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:locationdata.json").toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocationDTO[] locationDTOArray = gson.fromJson(responseJSON, LocationDTO[].class);
        LOGGER.info("Fetching location data from API finished");
        return locationDTOArray.length != 0 ? Arrays.asList(locationDTOArray) : Collections.emptyList();
    }

    @Override
    public List<ListingDTO> getListingList() {
        LOGGER.info("Fetching listing data from API");
//        String responseJSON = getResponseJSON(listingUrl);
        String responseJSON = "";
        try {
            responseJSON = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:listing.json").toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListingDTO[] listingDTOArray = gson.fromJson(responseJSON, ListingDTO[].class);
        LOGGER.info("Fetching listing data from API finished");
        return listingDTOArray.length != 0 ? Arrays.asList(listingDTOArray) : Collections.emptyList();
    }

    private String getUrl(String serviceUrl) {
        return String.format("%s%s%s", baseUrl, serviceUrl, apiKey);
    }

    private String getResponseJSON(String serviceUrl) {
        return restTemplate
                .exchange(getUrl(serviceUrl), HttpMethod.GET, null, String.class)
                .getBody();
    }
}
