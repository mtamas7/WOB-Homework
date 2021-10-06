package com.wob.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingStatusDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("status_name")
    private String status_name;

    public Long getId() {
        return id;
    }

    public String getStatusName() {
        return status_name;
    }

    public ListingStatusDTO(Long id, String status_name) {
        this.id = id;
        this.status_name = status_name;
    }
}
