package com.wob.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "listings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListingEntity {
    @Id
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @Column(name = "price")
    private double price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "quantity")
    private long quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "listing_status_id")
    private ListingStatusEntity listingStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marketplace_id")
    private MarketplaceEntity marketplace;

    @Column(name = "upload_time")
    private LocalDate uploadTime;

    @Column(name = "owner_email_address")
    private String ownerEmailAddress;
}
