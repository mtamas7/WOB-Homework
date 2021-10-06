package com.wob.homework.service.impl;

import com.wob.homework.service.ListingStatusService;
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
    ListingStatusService listingStatusService;

    private HomeworkServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new HomeworkServiceImpl(marketplaceService, listingStatusService);
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
}
