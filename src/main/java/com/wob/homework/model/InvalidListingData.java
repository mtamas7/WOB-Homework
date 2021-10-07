package com.wob.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class InvalidListingData {
    private UUID id;
    private String marketplaceName;
    private List<String> invalidFieldNames = new ArrayList<>();
}
