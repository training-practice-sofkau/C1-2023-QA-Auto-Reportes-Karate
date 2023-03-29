package com.sofkau.utils;

public enum ReqresInCreate {

    REQRES_BASE_URL("https://reqres.in"),
    REQRES_BASE_PAHT("/api/users");
    private final String value;

    ReqresInCreate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
