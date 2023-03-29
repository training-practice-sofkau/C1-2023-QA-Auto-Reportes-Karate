package com.sofkau.utils;

public enum CovidTest {

    COVID_BASE_URL("https://api.covidtracking.com"),
    COVID_BASE_PAHT("/v2/us/daily/"),
    COVID_BASE_PAHT2("/simple.json");

    private final String value;

    CovidTest(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
