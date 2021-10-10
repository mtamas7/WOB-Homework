package com.wob.homework.service.impl;

import com.wob.homework.dto.ListingDTO;
import com.wob.homework.entity.ListingStatusEntity;
import com.wob.homework.entity.MarketplaceEntity;
import com.wob.homework.model.InvalidListingData;
import com.wob.homework.model.ListingValidationResult;
import com.wob.homework.repository.ListingStatusRepository;
import com.wob.homework.repository.MarketplaceRepository;
import com.wob.homework.service.ListingValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListingValidatorServiceImpl implements ListingValidatorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListingValidatorServiceImpl.class);

    private static final String PRICE_REGEX = "^[0-9]+(\\.[0-9][0-9])$";
    private static final String EMAIL_REGEX = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";

    private final ListingStatusRepository listingStatusRepository;
    private final MarketplaceRepository marketplaceRepository;

    @Autowired
    public ListingValidatorServiceImpl(ListingStatusRepository listingStatusRepository, MarketplaceRepository marketplaceRepository) {
        this.listingStatusRepository = listingStatusRepository;
        this.marketplaceRepository = marketplaceRepository;
    }

    @Override
    public ListingValidationResult validateListings(List<ListingDTO> listingDTOList) {
        LOGGER.info("Listing data validation has been started...");
        List<ListingDTO> validListingList = new ArrayList<>();
        List<InvalidListingData> invalidListingData = new ArrayList<>();

        for (ListingDTO listingDTO : listingDTOList) {
            List<String> invalidFields = validate(listingDTO);
            if (invalidFields.isEmpty()) {
                validListingList.add(listingDTO);
            } else {
                invalidListingData.add(createValidationResult(listingDTO, invalidFields));
            }
        }
        LOGGER.info("Listing data validation finished ");
        return new ListingValidationResult(validListingList, invalidListingData);
    }

    private List<String> validate(ListingDTO listingDTO) {
        List<Long> marketplaceIdList = marketplaceRepository.findAll().stream().map(MarketplaceEntity::getId).collect(Collectors.toList());
        List<Long> listingStatusIdList = listingStatusRepository.findAll().stream().map(ListingStatusEntity::getId).collect(Collectors.toList());

        List<String> invalidFields = new ArrayList<>();
        if (!isValidTitle(listingDTO.getTitle())) invalidFields.add("title");
        if (!isValidDescription(listingDTO.getDescription())) invalidFields.add("description");
        if (!isValidListingPrice(listingDTO.getListingPrice())) invalidFields.add("listing_price");
        if (!isValidCurrency(listingDTO.getCurrency())) invalidFields.add("currency");
        if (!isValidQuantity(listingDTO.getQuantity())) invalidFields.add("quantity");
        if (!listingStatusIdList.contains(listingDTO.getListingStatus())) invalidFields.add("listing_status");
        if (!marketplaceIdList.contains(listingDTO.getMarketplace())) invalidFields.add("marketplace");
        if (!isValidOwnerEmailAddress(listingDTO.getOwnerEmailAddress())) invalidFields.add("owner_email_address");

        return invalidFields;
    }

    private boolean isValidTitle(String title) {
        return title != null;
    }

    private boolean isValidDescription(String description) {
        return description != null;
    }

    private boolean isValidListingPrice(Double listingPrice) {
        return listingPrice.toString().matches(PRICE_REGEX);
    }

    private boolean isValidCurrency(String currency) {
        return currency != null && currency.length() == 3;
    }

    private boolean isValidQuantity(long quantity) {
        return quantity > 0;
    }

    private boolean isValidOwnerEmailAddress(String emailAddress) {
        return emailAddress != null && emailAddress.matches(EMAIL_REGEX);
    }

    private InvalidListingData createValidationResult(ListingDTO listingDTO, List<String> invalidFields) {
        Optional<MarketplaceEntity> marketPlace = marketplaceRepository.findById(listingDTO.getMarketplace());
        String marketplaceName = marketPlace.isPresent() ? marketPlace.get().getMarketplaceName() : "Unknown";

        return new InvalidListingData(listingDTO.getId(), marketplaceName, invalidFields);
    }
}
