package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL("https://reqres.in/"),
    RESOURCE_GET("https://reqres.in/api/users?page=2");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
