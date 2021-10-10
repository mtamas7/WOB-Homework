package com.wob.homework.service.impl;

import com.wob.homework.service.ListingService;
import com.wob.homework.service.ListingStatusService;
import com.wob.homework.service.LocationService;
import com.wob.homework.service.MarketplaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class HomeworkServiceImplTest {

    @Mock
    private MarketplaceService marketplaceService;

    @Mock
    private ListingStatusService listingStatusService;

    @Mock
    private LocationService locationService;

    @Mock
    private ListingService listingService;

    private HomeworkServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new HomeworkServiceImpl(marketplaceService, listingStatusService, locationService, listingService);
    }

    @Test
    void shouldCallMarketplaceService() {
        underTest.fetchDataAndCreateReport();
        verify(marketplaceService).fetchAndSaveMarketplaceData();
    }

    @Test
    void shouldCallListingStatusService() {
        underTest.fetchDataAndCreateReport();
        verify(listingStatusService).fetchAndSaveListingStatusData();
    }

    @Test
    void shouldCallLocationService() {
        underTest.fetchDataAndCreateReport();
        verify(locationService).fetchAndSaveLocationData();
    }

    @Test
    void shouldCallListingService() {
        underTest.fetchDataAndCreateReport();
        verify(listingService).fetchAndSaveListingData();
    }
}
