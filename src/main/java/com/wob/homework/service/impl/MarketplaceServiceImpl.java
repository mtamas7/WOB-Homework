package com.wob.homework.service.impl;

import com.wob.homework.dto.MarketplaceDTO;
import com.wob.homework.entity.MarketPlaceEntity;
import com.wob.homework.repository.MarketplaceRepository;
import com.wob.homework.service.ApiService;
import com.wob.homework.service.MarketplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketplaceServiceImpl implements MarketplaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketplaceServiceImpl.class);

    private final ApiService apiService;
    private final MarketplaceRepository marketplaceRepository;

    @Autowired
    public MarketplaceServiceImpl(ApiService apiService, MarketplaceRepository marketplaceRepository) {
        this.apiService = apiService;
        this.marketplaceRepository = marketplaceRepository;
    }

    @Override
    public void fetchAndSaveMarketplaceData() {
        LOGGER.info("Marketplace data fetching is in progress...");
        List<MarketplaceDTO> marketPlaceList = apiService.getMarketplaceList();
        List<MarketPlaceEntity> marketPlaceEntities = marketPlaceList.stream()
                .map(this::mapMarketplaceDTO)
                .collect(Collectors.toList());
        if (!marketPlaceEntities.isEmpty()) {
            LOGGER.info("Marketplace data fetch is done, save entities into the database");
            marketplaceRepository.saveAll(marketPlaceEntities);
            LOGGER.info("Marketplace data save finished");
        } else {
            LOGGER.info("Empty response, no saving to the database");
        }
    }

    private MarketPlaceEntity mapMarketplaceDTO(MarketplaceDTO marketPlaceDTO) {
        return new MarketPlaceEntity(marketPlaceDTO.getId(), marketPlaceDTO.getMarketplaceName());
    }
}
