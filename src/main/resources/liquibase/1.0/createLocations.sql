CREATE TABLE locations
(
    id                varchar(255) NOT NULL,
    address_primary   varchar(255) DEFAULT NULL,
    address_secondary varchar(255) DEFAULT NULL,
    country           varchar(255) DEFAULT NULL,
    manager_name      varchar(255) DEFAULT NULL,
    phone             varchar(255) DEFAULT NULL,
    postal_code       varchar(255) DEFAULT NULL,
    town              varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);
