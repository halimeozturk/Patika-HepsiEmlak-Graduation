package com.example.payment.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentStatus {
    APPROVED("Onaylandı"),
    REJECTED("Reddedildi"),
    COMPLETED("Tamamlandı"),
    CANCELLED("İptal Edildi");

    private final String screenLabel;

}
