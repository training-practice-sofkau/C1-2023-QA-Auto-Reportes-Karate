package com.sofkau.models;

public class RegistrarUsuario {
    private String name;
    private String job;

    public RegistrarUsuario() {
    }

    public RegistrarUsuario(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String name() {
        return name;
    }

    public RegistrarUsuario setName(String name) {
        this.name = name;
        return this;
    }

    public String job() {
        return job;
    }

    public RegistrarUsuario setJob(String job) {
        this.job = job;
        return this;
    }
}
