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
@Table(name = "marketplaces")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarketplaceEntity {
    @Id
    private Long id;

    @Column(name = "marketplace_name")
    private String marketplaceName;
}
