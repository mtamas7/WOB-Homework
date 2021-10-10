package com.wob.homework.service.impl;

import com.wob.homework.dto.ListingStatusDTO;
import com.wob.homework.entity.ListingStatusEntity;
import com.wob.homework.repository.ListingStatusRepository;
import com.wob.homework.service.ApiService;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListingStatusServiceImplTest {
    private static final Long MOCK_LISTING_STATUS_ID = 1L;
    private static final String MOCK_LISTING_STATUS_NAME = "mockName";

    @Mock
    private ApiService apiService;

    @Mock
    private ListingStatusRepository listingStatusRepository;

    @Captor
    private ArgumentCaptor<List<ListingStatusEntity>> argumentCaptor;

    private ListingStatusServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ListingStatusServiceImpl(apiService, listingStatusRepository);
    }

    @Test
    void shouldReturnListingStatusEntityList() {
        when(apiService.getListingStatusList()).thenReturn(Collections.singletonList(new ListingStatusDTO(MOCK_LISTING_STATUS_ID, MOCK_LISTING_STATUS_NAME)));

        underTest.fetchAndSaveListingStatusData();

        verify(listingStatusRepository).saveAll(argumentCaptor.capture());
        assertEquals(1, argumentCaptor.getValue().size());
        assertEquals(MOCK_LISTING_STATUS_ID, argumentCaptor.getValue().get(0).getId());
        assertEquals(MOCK_LISTING_STATUS_NAME, argumentCaptor.getValue().get(0).getStatusName());
    }

    @Test
    void shouldNotCallRepositoryIfResponseIsEmpty() {
        when(apiService.getListingStatusList()).thenReturn(Collections.emptyList());

        underTest.fetchAndSaveListingStatusData();

        verify(listingStatusRepository, never()).saveAll(any());
    }
}
