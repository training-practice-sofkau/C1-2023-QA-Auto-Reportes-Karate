package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL_BAYAS("https://pokeapi.co/api/v2/"),
    RESOURCE_BAYAS("berry/"),
    REQRES_BASE_URL_PUT("https://reqres.in/api/"),
    RESOURCES_PUT("users/");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
