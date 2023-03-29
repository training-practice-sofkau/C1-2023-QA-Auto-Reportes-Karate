package com.sofkau.utils;

public enum ReqresGetResources {
    REQRES_BASE_URL_GET("https://reqres.in/api"),
    RESOURCE_GET("/users/");

    private final String value;

    ReqresGetResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}