CREATE TABLE listings
(
    id                  varchar(255) NOT NULL,
    currency            varchar(255) DEFAULT NULL,
    description         varchar(255) DEFAULT NULL,
    owner_email_address varchar(255) DEFAULT NULL,
    price               double       DEFAULT NULL,
    quantity            bigint       DEFAULT NULL,
    title               varchar(255) DEFAULT NULL,
    upload_time         date         DEFAULT NULL,
    listing_status_id   bigint       DEFAULT NULL,
    location_id         varchar(255) DEFAULT NULL,
    marketplace_id      bigint       DEFAULT NULL,
    PRIMARY KEY (id),
    KEY                 listing_status_id(listing_status_id),
    KEY                 location_id(location_id),
    KEY                 marketplace_id(marketplace_id),
    CONSTRAINT fk_marketplace FOREIGN KEY (marketplace_id) REFERENCES marketplaces (id),
    CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES locations (id),
    CONSTRAINT fk_listing_status_id FOREIGN KEY (listing_status_id) REFERENCES listing_statuses (id)
);
