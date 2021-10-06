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
@Table(name = "listing_statuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListingStatusEntity {
    @Id
    private Long id;

    @Column(name = "status_name")
    private String statusName;
}
