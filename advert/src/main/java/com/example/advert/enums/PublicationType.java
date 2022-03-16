package com.example.advert.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PublicationType {
    FOR_RENT("Kiralık"),
    FOR_SALE("Satılık"),
    DAILY_RENT("Günlük Kiralık");

    private final String screenLabel;

    }
