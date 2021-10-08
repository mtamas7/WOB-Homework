package com.wob.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class InvalidListingData {
    private String id;
    private String marketplaceName;
    private List<String> invalidFieldNames;
}
