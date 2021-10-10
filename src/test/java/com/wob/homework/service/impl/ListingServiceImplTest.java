package com.wob.homework.service.impl;

import com.wob.homework.dto.ListingDTO;
import com.wob.homework.entity.ListingEntity;
import com.wob.homework.model.InvalidListingData;
import com.wob.homework.model.ListingValidationResult;
import com.wob.homework.repository.ListingRepository;
import com.wob.homework.repository.ListingStatusRepository;
import com.wob.homework.repository.LocationRepository;
import com.wob.homework.repository.MarketplaceRepository;
import com.wob.homework.service.ApiService;
import com.wob.homework.service.ListingValidatorService;
import com.wob.homework.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListingServiceImplTest {
    private static final String MOCK_ID = "mockId";
    private static final String MOCK_MARKETPLACE_NAME = "mockMarketplaceName";
    private static final String MOCK_FIELD_NAME = "mockFieldName";
    private static final String MOCK_DATE = "01/01/01";
    private static final String EMPTY_STRING = "";

    @Mock
    private ApiService apiService;

    @Mock
    private ListingValidatorService listingValidatorService;

    @Mock
    private ReportService reportService;

    @Mock
    private ListingRepository listingRepository;

    @Mock
    private ListingStatusRepository listingStatusRepository;

    @Mock
    private MarketplaceRepository marketplaceRepository;

    @Mock
    private LocationRepository locationRepository;

    @Captor
    private ArgumentCaptor<List<ListingEntity>> argumentCaptor;

    private ListingServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ListingServiceImpl(apiService,
                listingValidatorService,
                reportService,
                listingRepository,
                listingStatusRepository,
                marketplaceRepository,
                locationRepository);
    }

    @Test
    void shouldFetchAndSaveDataAndCreateReport() {
        when(apiService.getListingList()).thenReturn(Collections.singletonList(getMockListingDTO()));

        InvalidListingData mockInvalidListingData = new InvalidListingData(MOCK_ID, MOCK_MARKETPLACE_NAME, Collections.singletonList(MOCK_FIELD_NAME));
        ListingValidationResult mockValidationResult
                = new ListingValidationResult(Collections.singletonList(getMockListingDTO()), Collections.singletonList(mockInvalidListingData));
        when(listingValidatorService.validateListings(anyList())).thenReturn(mockValidationResult);

        underTest.fetchAndSaveListingData();

        verify(reportService).createLogFileForInvalidData(anyList());
        verify(listingRepository).saveAll(argumentCaptor.capture());
        verify(reportService).createAndUploadReport();
        assertEquals(1, argumentCaptor.getValue().size());
        assertEquals(MOCK_ID, argumentCaptor.getValue().get(0).getId());
    }

    @Test
    void shouldNotCallRepositoryAndService() {
        when(apiService.getListingList()).thenReturn(Collections.emptyList());
        ListingValidationResult mockValidationResult
                = new ListingValidationResult(Collections.emptyList(), Collections.emptyList());
        when(listingValidatorService.validateListings(anyList())).thenReturn(mockValidationResult);

        verify(reportService, never()).createLogFileForInvalidData(anyList());
        verify(reportService, never()).createAndUploadReport();
        verify(listingRepository, never()).saveAll(any());

    }

    private ListingDTO getMockListingDTO() {
        return new ListingDTO(MOCK_ID, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, 0.0, EMPTY_STRING, 1, 1, 1, MOCK_DATE, EMPTY_STRING);
    }
}
