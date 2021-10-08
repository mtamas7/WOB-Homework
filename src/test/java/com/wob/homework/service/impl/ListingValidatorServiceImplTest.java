package com.wob.homework.service.impl;


import com.wob.homework.dto.ListingDTO;
import com.wob.homework.entity.ListingStatusEntity;
import com.wob.homework.entity.MarketplaceEntity;
import com.wob.homework.model.ListingValidationResult;
import com.wob.homework.repository.ListingStatusRepository;
import com.wob.homework.repository.MarketplaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ListingValidatorServiceImplTest {
    private static final Long MOCK_ID = 1L;
    private static final String MOCK_MARKETPLACE_NAME = "mockName";
    private static final String MOCK_LISTING_STATUS_NAME = "mockStatusName";
    private static final String MOCK_UUID = "ac867cd8-c321-42ab-b179-01a4b8301a9c";
    private static final String MOCK_TITLE = "mockTitle";
    private static final String MOCK_DESCRIPTION = "mockDescription";
    private static final String MOCK_LOCATION_ID = "bc867cd8-c321-42ab-b179-01a4b8301a9c";
    private static final double MOCK_LISTING_PRICE = 52.91;
    private static final String MOCK_CURRENCY = "CUR";
    private static final long MOCK_QUANTITY = 10;
    private static final long MOCK_LISTING_STATUS = 1;
    private static final long MOCK_MARKETPLACE = 1;
    private static final String MOCK_UPLOAD_DATE = "mockDate";
    private static final String MOCK_EMAIL = "test@test.hu";


    @Mock
    private ListingStatusRepository listingStatusRepository;
    @Mock
    private MarketplaceRepository marketplaceRepository;

    private ListingValidatorServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ListingValidatorServiceImpl(listingStatusRepository, marketplaceRepository);

        when(marketplaceRepository.findAll())
                .thenReturn(Collections.singletonList(new MarketplaceEntity(MOCK_ID, MOCK_MARKETPLACE_NAME)));
        when(listingStatusRepository.findAll())
                .thenReturn(Collections.singletonList(new ListingStatusEntity(MOCK_ID, MOCK_LISTING_STATUS_NAME)));
    }

    @Test
    void shouldReturnValidResponseForValidation() {
        ListingValidationResult listingValidationResult = underTest.validateListings(Collections.singletonList(getMockListingDTO()));
        assertEquals(1, listingValidationResult.getValidListingList().size());
        assertEquals(0, listingValidationResult.getInvalidListingData().size());
    }

    @Test
    void shouldReturnInvalidResponseForValidationIfDataIsNotValidAndMarketplaceNameShouldBeUnknown() {
        ListingDTO mockListingDTO = getMockListingDTO();
        mockListingDTO.setOwnerEmailAddress("invalidEmail");

        ListingValidationResult listingValidationResult = underTest.validateListings(Collections.singletonList(mockListingDTO));
        assertEquals(0, listingValidationResult.getValidListingList().size());
        assertEquals(1, listingValidationResult.getInvalidListingData().size());
        assertEquals(1, listingValidationResult.getInvalidListingData().get(0).getInvalidFieldNames().size());
        assertEquals("owner_email_address", listingValidationResult.getInvalidListingData().get(0).getInvalidFieldNames().get(0));
        assertEquals("Unknown", listingValidationResult.getInvalidListingData().get(0).getMarketplaceName());
    }

    @Test
    void shouldReturnInvalidResponseForValidationIfDataIsNotValid() {
        when(marketplaceRepository.findById(anyLong())).thenReturn(Optional.of(new MarketplaceEntity(MOCK_ID, MOCK_MARKETPLACE_NAME)));

        ListingDTO mockListingDTO = getMockListingDTO();
        mockListingDTO.setDescription(null);

        ListingValidationResult listingValidationResult = underTest.validateListings(Collections.singletonList(mockListingDTO));
        assertEquals(0, listingValidationResult.getValidListingList().size());
        assertEquals(1, listingValidationResult.getInvalidListingData().size());
        assertEquals(1, listingValidationResult.getInvalidListingData().get(0).getInvalidFieldNames().size());
        assertEquals("description", listingValidationResult.getInvalidListingData().get(0).getInvalidFieldNames().get(0));
        assertEquals("mockName", listingValidationResult.getInvalidListingData().get(0).getMarketplaceName());
    }

    private ListingDTO getMockListingDTO() {
        return new ListingDTO(MOCK_UUID,
                MOCK_TITLE,
                MOCK_DESCRIPTION,
                MOCK_LOCATION_ID,
                MOCK_LISTING_PRICE,
                MOCK_CURRENCY,
                MOCK_QUANTITY,
                MOCK_LISTING_STATUS,
                MOCK_MARKETPLACE,
                MOCK_UPLOAD_DATE,
                MOCK_EMAIL);
    }

}
