package com.sofkau.models;

public class PeticionCrearUsuario {

    private String name;
    private String job;


    public PeticionCrearUsuario(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public PeticionCrearUsuario() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
