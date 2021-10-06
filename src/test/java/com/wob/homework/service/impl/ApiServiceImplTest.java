package com.wob.homework.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wob.homework.dto.MarketPlaceDTO;
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

    private static final String MOCK_MARKETPLACE_NAME = "mockName";
    private static final Long MOCK_MARKETPLACE_ID = 1L;
    private static final String MOCK_MARKETPLACE_JSON = "[{\"id\": " + MOCK_MARKETPLACE_ID + ", \"marketplace_name\": \"" + MOCK_MARKETPLACE_NAME + "\"}]";
    private static final String MOCK_MARKETPLACE_EMPTY_JSON = "[]";

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

        List<MarketPlaceDTO> marketplaceList = underTest.getMarketplaceList();

        assertEquals(1, marketplaceList.size());
        assertEquals(MOCK_MARKETPLACE_ID, marketplaceList.get(0).getId());
        assertEquals(MOCK_MARKETPLACE_NAME, marketplaceList.get(0).getMarketplaceName());


    }

    @Test
    void shouldReturnEmptyListIfResponseIsEmpty() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(MOCK_MARKETPLACE_EMPTY_JSON, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        List<MarketPlaceDTO> marketplaceList = underTest.getMarketplaceList();
        assertTrue(marketplaceList.isEmpty());
    }

}
