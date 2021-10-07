package com.wob.homework.service;

import com.wob.homework.dto.ListingDTO;
import com.wob.homework.dto.ListingStatusDTO;
import com.wob.homework.dto.LocationDTO;
import com.wob.homework.dto.MarketplaceDTO;

import java.util.List;

public interface ApiService {
    /**
     * Fetch the marketplace list from the API.
     *
     * @return List<MarketPlaceDTO> the list of the marketplaces otherwise
     * it will be an empty list if the response did not contain the data
     */
    List<MarketplaceDTO> getMarketplaceList();

    /**
     * Fetch the listing status list from the API.
     *
     * @return List<ListingStatusDTO> the list of the listing statuses otherwise
     * it will be an empty list if the response did not contain the data
     */
    List<ListingStatusDTO> getListingStatusList();

    /**
     * Fetch the location list from the API.
     *
     * @return List<LocationDTO> the list of the locations otherwise
     * it will be an empty list if the response did not contain the data
     */
    List<LocationDTO> getLocationList();

    /**
     * Fetch the listing list from the API.
     *
     * @return List<ListingDTO> the list of the listings otherwise
     * it will be an empty list if the response did not contain the data
     */
    List<ListingDTO> getListingList();
}
