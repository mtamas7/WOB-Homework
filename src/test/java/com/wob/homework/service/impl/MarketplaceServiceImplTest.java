package com.wob.homework.service.impl;

import com.wob.homework.dto.MarketplaceDTO;
import com.wob.homework.entity.MarketPlaceEntity;
import com.wob.homework.repository.MarketplaceRepository;
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


class MarketplaceServiceImplTest {
    private static final String MOCK_MARKETPLACE_NAME = "mockName";
    private static final Long MOCK_MARKETPLACE_ID = 1L;

    @Mock
    private ApiService apiService;

    @Mock
    private MarketplaceRepository marketplaceRepository;

    @Captor
    private ArgumentCaptor<List<MarketPlaceEntity>> argumentCaptor;

    private MarketplaceServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MarketplaceServiceImpl(apiService, marketplaceRepository);
    }

    @Test
    void shouldReturnMarketplaceEntityList() {
        when(apiService.getMarketplaceList()).thenReturn(Collections.singletonList(new MarketplaceDTO(MOCK_MARKETPLACE_ID, MOCK_MARKETPLACE_NAME)));

        underTest.fetchAndSaveMarketplaceData();

        verify(marketplaceRepository).saveAll(argumentCaptor.capture());
        assertEquals(1, argumentCaptor.getValue().size());
        assertEquals(MOCK_MARKETPLACE_ID, argumentCaptor.getValue().get(0).getId());
        assertEquals(MOCK_MARKETPLACE_NAME, argumentCaptor.getValue().get(0).getMarketplaceName());
    }

    @Test
    void shouldNotCallRepositoryIfResponseIsEmpty() {
        when(apiService.getMarketplaceList()).thenReturn(Collections.emptyList());

        underTest.fetchAndSaveMarketplaceData();

        verify(marketplaceRepository, never()).saveAll(any());
    }
}
