package com.sofkau.utils;

public enum Constants {
    ACTOR("REQRES Admin"),
    REQRES_BASE_URL("https://reqres.in"),
    RESOURCE("/api/users/%s"),
    USER_SCHEMATIC("{\"name\": %s %s \"job\": %s}"),
    NAME_1("\"morpheus\""),
    JOB_1("\"leader\""),
    NAME_2("\"neo\""),
    JOB_2("\"the chosen\"");

    private final String value;

    Constants(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}