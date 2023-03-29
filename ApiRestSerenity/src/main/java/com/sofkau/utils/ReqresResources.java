package com.sofkau.utils;

public enum ReqresResources {
    LISTAR_PRODUCTOS_BASE_URL("https://fakestoreapi.com/"),
    RESOURCE_LISTAR_PRODUCTOS("products?limit="),
    LISTAR_FRASES_BASE_URL("https://dummyjson.com/"),
    RESOURCE_LISTAR_FRASES ("quotes/"),
    CREAR_COMENTARIO_BASE_URL("https://dummyjson.com/"),
    RESOURCE_COMENTARIO_URL("comments/add");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
