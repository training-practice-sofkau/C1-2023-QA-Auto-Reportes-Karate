package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL("https://reqres.in/"),
    REGISTER_SUCCESSFUL_RESOURCE("api/register"),
    JSON_PLACE_HOLDER("https://jsonplaceholder.typicode.com/"),
    GET_RESOURCE("posts/"),
    POST_RESOURCE("posts"),
    PUT_RESOURCE("posts/");

    private final String  value;

    ReqresResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
