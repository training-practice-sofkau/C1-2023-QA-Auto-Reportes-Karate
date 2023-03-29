package com.sofkau.utils;

public enum JsonPlaceHolderResources {
    JSON_PLACE_HOLDER("https://jsonplaceholder.typicode.com/"),
    GET_RESOURCE("posts/");

    private final String  value;

    JsonPlaceHolderResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
