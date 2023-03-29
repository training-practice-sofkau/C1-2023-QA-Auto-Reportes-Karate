package com.sofkau.utils;

public enum ReqresResources {
    ANIME_JINKAN_BASE_URL("https://api.jikan.moe/v4/"),
    ANIME_JINKAN_RESOURCE("anime/");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
