package com.wob.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ListingDTO {
    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "location_id")
    private UUID location_id;

    @JsonProperty(value = "listing_price")
    private double listing_price;

    @JsonProperty(value = "currency")
    private String currency;

    @JsonProperty(value = "quantity")
    private long quantity;

    @JsonProperty(value = "listing_status")
    private long listing_status;

    @JsonProperty(value = "marketplace")
    private long marketplace;

    @JsonProperty(value = "upload_time", access = JsonProperty.Access.READ_ONLY)
    private String upload_time;

    @JsonProperty(value = "owner_email_address")
    private String owner_email_address;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getLocationId() {
        return location_id;
    }

    public void setLocationId(UUID locationId) {
        this.location_id = locationId;
    }

    public double getListingPrice() {
        return listing_price;
    }

    public void setListing_price(double listingPrice) {
        this.listing_price = listingPrice;
    }

    public long getListingStatus() {
        return listing_status;
    }

    public void setListing_status(long listingStatus) {
        this.listing_status = listingStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public long getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(long marketplace) {
        this.marketplace = marketplace;
    }

    public String getUploadTime() {
        return upload_time;
    }

    public void setUploadTime(String uploadTime) {
        this.upload_time = uploadTime;
    }

    public String getOwnerEmailAddress() {
        return owner_email_address;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.owner_email_address = ownerEmailAddress;
    }

    public ListingDTO(UUID id, String title, String description, UUID locationId, double price, String currency, long quantity, long status, long marketplace, String uploadTime, String ownerEmailAddress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location_id = locationId;
        this.listing_price = price;
        this.currency = currency;
        this.quantity = quantity;
        this.listing_status = status;
        this.marketplace = marketplace;
        this.upload_time = uploadTime;
        this.owner_email_address = ownerEmailAddress;
    }
}
