package com.wob.homework.model;

import com.wob.homework.dto.ListingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListingValidationResult {
    private List<ListingDTO> validListingList;
    private List<InvalidListingData> invalidListingData;
}
