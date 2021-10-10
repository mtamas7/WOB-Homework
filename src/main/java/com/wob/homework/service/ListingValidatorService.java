package com.wob.homework.service;

import com.wob.homework.dto.ListingDTO;
import com.wob.homework.model.ListingValidationResult;

import java.util.List;

public interface ListingValidatorService {
    /**
     * Validates the collection of listings.
     * If one of them did not comply with the validation rules,
     * the response will include a list of the items concerned, otherwise it will contain an empty list
     *
     * @param listingDTOList listings to validate
     * @return ListingValidationResult the result of the validation
     */
    ListingValidationResult validateListings(List<ListingDTO> listingDTOList);
}
