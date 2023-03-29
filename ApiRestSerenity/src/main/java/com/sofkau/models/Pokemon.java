package com.sofkau.models;


import java.util.List;

public class Pokemon {
    private String name;

    private List<String> abilities;

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
