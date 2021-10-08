package com.wob.homework.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wob.homework.dto.ListingDTO;
import com.wob.homework.dto.ListingStatusDTO;
import com.wob.homework.dto.LocationDTO;
import com.wob.homework.dto.MarketplaceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ApiServiceImplTest {

    private static final Long MOCK_ID = 1L;
    private static final String MOCK_UUID = "ac867cd8-c321-42ab-b179-01a4b8301a9c";
    private static final String MOCK_MARKETPLACE_NAME = "mockMarketplaceName";
    private static final String MOCK_LISTING_STATUS_NAME = "mockListingStatusName";
    private static final String MOCK_MANAGER_NAME = "Harry Potter";
    private static final String MOCK_TITLE_NAME = "It";
    private static final String MOCK_MARKETPLACE_JSON = "[{\"id\": " + MOCK_ID + ", \"marketplace_name\": \"" + MOCK_MARKETPLACE_NAME + "\"}]";
    private static final String MOCK_LISTING_STATUS_JSON = "[{\"id\": " + MOCK_ID + ", \"status_name\": \"" + MOCK_LISTING_STATUS_NAME + "\"}]";
    private static final String MOCK_LOCATION_JSON = "[{\"id\": \"" + MOCK_UUID + "\",\"manager_name\": \"" + MOCK_MANAGER_NAME + "\",\"phone\": \"338-725-3223\",\"address_primary\": \"4 Privet Drive\",\"address_secondary\": null,\"country\": \"England\",\"town\": \"Little Whinging\",\"postal_code\": null}]";
    private static final String MOCK_LISTING_JSON = "[{\"id\": \"" + MOCK_UUID + "\",\"title\": \"" + MOCK_TITLE_NAME + "\",\"description\": \"Arabis lemmonii S. Watson var. depauperata (A. Nelson & Kennedy) Rollins\",\"location_id\": \"5ae22efb-f875-4134-a03d-6402efa31dc5\",\"listing_price\": 629.34,\"currency\": \"EUR\",\"quantity\": 1,\"listing_status\": 2,\"marketplace\": 2,\"upload_time\": \"6/23/2017\",\"owner_email_address\": \"jgwang@aol.com\"}]";
    private static final String MOCK_EMPTY_JSON = "[]";

    @Mock
    private RestTemplate restTemplate;

    private ApiServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Gson gson = new GsonBuilder().create();
        underTest = new ApiServiceImpl(restTemplate, gson);
    }

    @Test
    void shouldReturnMarketplaceList() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_MARKETPLACE_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);

        List<MarketplaceDTO> marketplaceList = underTest.getMarketplaceList();

        assertEquals(1, marketplaceList.size());
        assertEquals(MOCK_ID, marketplaceList.get(0).getId());
        assertEquals(MOCK_MARKETPLACE_NAME, marketplaceList.get(0).getMarketplaceName());
    }

    @Test
    void shouldReturnEmptyListIfMarketplaceResponseIsEmpty() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_EMPTY_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        List<MarketplaceDTO> marketplaceList = underTest.getMarketplaceList();
        assertTrue(marketplaceList.isEmpty());
    }

    @Test
    void shouldReturnListingStatusList() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_LISTING_STATUS_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);

        List<ListingStatusDTO> listingStatusList = underTest.getListingStatusList();

        assertEquals(1, listingStatusList.size());
        assertEquals(MOCK_ID, listingStatusList.get(0).getId());
        assertEquals(MOCK_LISTING_STATUS_NAME, listingStatusList.get(0).getStatusName());
    }

    @Test
    void shouldReturnEmptyListIfListingStatusResponseIsEmpty() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_EMPTY_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        List<ListingStatusDTO> listingStatusList = underTest.getListingStatusList();
        assertTrue(listingStatusList.isEmpty());
    }

    @Test
    void shouldReturnLocationList() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_LOCATION_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);

        List<LocationDTO> locationList = underTest.getLocationList();

        assertEquals(1, locationList.size());
        assertEquals(MOCK_UUID, locationList.get(0).getId());
        assertEquals(MOCK_MANAGER_NAME, locationList.get(0).getManagerName());
    }

    @Test
    void shouldReturnEmptyListIfLocationResponseIsEmpty() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_EMPTY_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        List<LocationDTO> locationList = underTest.getLocationList();
        assertTrue(locationList.isEmpty());
    }

    @Test
    void shouldReturnListingList() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_LISTING_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);

        List<ListingDTO> listingList = underTest.getListingList();

        assertEquals(1, listingList.size());
        assertEquals(UUID.fromString(MOCK_UUID), listingList.get(0).getId());
        assertEquals(MOCK_TITLE_NAME, listingList.get(0).getTitle());
    }

    @Test
    void shouldReturnEmptyListIfListingResponseIsEmpty() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_EMPTY_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        List<ListingDTO> listingList = underTest.getListingList();
        assertTrue(listingList.isEmpty());
    }
}
