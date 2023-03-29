package com.sofkau.utils;

public enum ReqresPutResources {
    REQRES_BASE_URL_PUT("https://reqres.in/api"),
    RESOURCE_PUT("/users/");

    private final String value;

    ReqresPutResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}