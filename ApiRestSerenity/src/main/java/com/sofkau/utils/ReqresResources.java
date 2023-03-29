package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL("https://reqres.in/api"),
    RESOURCE("/users"),
    ATTRIBUTE_NAME("name"),
    ATTRIBUTE_JOB("job");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
