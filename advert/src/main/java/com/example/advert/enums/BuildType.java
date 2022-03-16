package com.example.advert.enums;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BuildType {
    WOODEN_HOUSE("Ahşap Ev"),
    EARTH_HOUSE("Toprak Ev"),
    STONE_HOUSE("Taş Ev"),
    ADOBE_HOUSE("Kerpiç Ev"),
    TILE_HOUSE("Kiremit Ev"),
    REINFORCED_CONCRETE_HOUSE("Betonarme Ev");

    private final String screenLabel;

    }
