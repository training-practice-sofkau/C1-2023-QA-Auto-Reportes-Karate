package com.sofkau.utils;

public enum BookingResources {

    BOOKING_BASE_URL("https://restful-booker.herokuapp.com/"),
    BOOKING_RESOURCES("auth");

    private final String value;

    BookingResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
