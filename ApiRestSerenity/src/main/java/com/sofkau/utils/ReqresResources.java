package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL("https://reqres.in/"),
    LOGIN_RESOURCE("api/login"),
    SINGLE_USER_RESOURCE("api/users/");


    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
