package com.sofkau.utils;

public enum AlbumsResources {
    ALBUMS_BASE_URL("https://jsonplaceholder.typicode.com"),


    RESOURCE_ALBUM("/albums/");


    private final String  value;

    AlbumsResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
