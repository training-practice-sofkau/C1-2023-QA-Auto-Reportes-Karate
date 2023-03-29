package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL(""),
    RESOURCE(""),
    JSONPLACEHOLDER_BASE_URL("https://jsonplaceholder.typicode.com"),
    TAREA_POST_RESOURCE("/todos");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
