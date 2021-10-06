package com.wob.homework.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wob.homework.dto.ListingStatusDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ApiServiceImplTest {

    private static final Long MOCK_ID = 1L;
    private static final String MOCK_MARKETPLACE_NAME = "mockMarketplaceName";
    private static final String MOCK_LISTING_STATUS_NAME = "mockListingStatusName";
    private static final String MOCK_MARKETPLACE_JSON = "[{\"id\": " + MOCK_ID + ", \"marketplace_name\": \"" + MOCK_MARKETPLACE_NAME + "\"}]";
    private static final String MOCK_LISTING_STATUS_JSON = "[{\"id\": " + MOCK_ID + ", \"status_name\": \"" + MOCK_LISTING_STATUS_NAME + "\"}]";
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
}
