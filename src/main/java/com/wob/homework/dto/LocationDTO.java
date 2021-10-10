package com.wob.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("manager_name")
    private String manager_name;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("address_primary")
    private String address_primary;

    @JsonProperty("address_secondary")
    private String address_secondary;

    @JsonProperty("country")
    private String country;

    @JsonProperty("town")
    private String town;

    @JsonProperty("postal_code")
    private String postal_code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManagerName() {
        return manager_name;
    }

    public void setManagerName(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressPrimary() {
        return address_primary;
    }

    public void setAddressPrimary(String address_primary) {
        this.address_primary = address_primary;
    }

    public String getAddressSecondary() {
        return address_secondary;
    }

    public void setAddressSecondary(String address_secondary) {
        this.address_secondary = address_secondary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(String postal_code) {
        this.postal_code = postal_code;
    }

    public LocationDTO(String id, String manager_name, String phone, String address_primary, String address_secondary, String country, String town, String postal_code) {
        this.id = id;
        this.manager_name = manager_name;
        this.phone = phone;
        this.address_primary = address_primary;
        this.address_secondary = address_secondary;
        this.country = country;
        this.town = town;
        this.postal_code = postal_code;
    }
}
