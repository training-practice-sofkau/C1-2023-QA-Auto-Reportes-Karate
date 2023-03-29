package com.sofkau.utils;

public enum ReqresResources {
    REQRES_BASE_URL("https://reqres.in/"),
    LISTAR_RECURSO_SUCCESSFUL_RESOURCE("api/unknown/"),
    REGISTER_SUCCESSFUL_RESOURCE("api/users");
    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
