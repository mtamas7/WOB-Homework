package com.wob.homework.service;

import com.wob.homework.dto.MarketPlaceDTO;

import java.util.List;

public interface ApiService {
    /**
     * Fetch the marketplace list from the API.
     *
     * @return List<MarketPlaceDTO> the list of the marketplaces otherwise
     * it will be an empty list if the response did not contain the data
     */
    List<MarketPlaceDTO> getMarketplaceList();
}
