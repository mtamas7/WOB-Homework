package com.wob.homework.service.impl;

import com.wob.homework.dto.LocationDTO;
import com.wob.homework.entity.LocationEntity;
import com.wob.homework.repository.LocationRepository;
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

class LocationServiceImplTest {

    private static final String MOCK_UUID = "ac867cd8-c321-42ab-b179-01a4b8301a9c";
    private static final String MOCK_MANAGER_NAME = "mockName";

    @Mock
    private ApiService apiService;

    @Mock
    private LocationRepository locationRepository;

    @Captor
    private ArgumentCaptor<List<LocationEntity>> argumentCaptor;

    private LocationServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new LocationServiceImpl(apiService, locationRepository);
    }

    @Test
    void shouldReturnListingStatusEntityList() {
        when(apiService.getLocationList()).thenReturn(Collections.singletonList(new LocationDTO(MOCK_UUID, MOCK_MANAGER_NAME, null, null, null, null, null, null)));

        underTest.fetchAndSaveLocationData();

        verify(locationRepository).saveAll(argumentCaptor.capture());
        assertEquals(1, argumentCaptor.getValue().size());
        assertEquals(MOCK_UUID, argumentCaptor.getValue().get(0).getId());
        assertEquals(MOCK_MANAGER_NAME, argumentCaptor.getValue().get(0).getManagerName());
    }

    @Test
    void shouldNotCallRepositoryIfResponseIsEmpty() {
        when(apiService.getLocationList()).thenReturn(Collections.emptyList());

        underTest.fetchAndSaveLocationData();

        verify(locationRepository, never()).saveAll(any());
    }
}
