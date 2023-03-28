package com.sofkau.utils;

public enum ReqresResources {

    BASE_JSON_URL("https://jsonplaceholder.typicode.com/"),
    DELETE_ALBUM("albums/"),
    POST_RESOURCE("posts/");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
