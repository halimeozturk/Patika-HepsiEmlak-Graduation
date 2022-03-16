package com.example.user.enums;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserType {
    INDIVIDUAL("Bireysel"),
    INSTITUTIONAL("Kurumsal");

    private final String screenLabel;

}
