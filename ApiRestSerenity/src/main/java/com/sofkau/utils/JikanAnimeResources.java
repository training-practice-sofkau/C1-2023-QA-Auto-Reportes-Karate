package com.sofkau.utils;

public enum JikanAnimeResources {

    ANIME_JINKAN_BASE_URL("https://api.jikan.moe/v4"),
    ANIME_JINKAN_RESOURCE("/anime/");
    private final String value;

    JikanAnimeResources(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
