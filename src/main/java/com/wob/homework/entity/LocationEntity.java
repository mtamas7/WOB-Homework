package com.wob.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {
    @Id
    private String id;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address_primary")
    private String addressPrimary;

    @Column(name = "address_secondary")
    private String addressSecondary;

    @Column(name = "country")
    private String country;

    @Column(name = "town")
    private String town;

    @Column(name = "postal_code")
    private String postalCode;
}
