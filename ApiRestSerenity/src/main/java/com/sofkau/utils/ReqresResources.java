package com.sofkau.utils;

public enum ReqresResources {
    DUMMYJSON_BASE_URL("https://dummyjson.com/"),
    DUMMYJSON_PUT_RESOURCE("users/"),
    COVIDTRACKING_BASE_URL("https://api.covidtracking.com/"),
    COVIDTRACKING_GET_RESOURCE("v1/states/ny/daily.json");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
