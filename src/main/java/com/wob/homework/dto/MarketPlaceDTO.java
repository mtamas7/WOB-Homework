package com.wob.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketPlaceDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("marketplace_name")
    private String marketplace_name;

    public Long getId() {
        return id;
    }

    public String getMarketplaceName() {
        return marketplace_name;
    }

    public MarketPlaceDTO(Long id, String marketplace_name) {
        this.id = id;
        this.marketplace_name = marketplace_name;
    }
}
