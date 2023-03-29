package com.sofkau.utils;

public enum ReqresResources {

    BASE_JSON_URL("https://jsonplaceholder.typicode.com/"),
    POST_RESOURCE("posts/"),
    BASE_POKE_URL("https://pokeapi.co/api/v2/"),
    POKEMON_RESOURCE("pokemon/");

    private final String value;

    ReqresResources(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}