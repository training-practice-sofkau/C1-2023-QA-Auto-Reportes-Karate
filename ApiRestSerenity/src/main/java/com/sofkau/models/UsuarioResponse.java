package com.sofkau.models;

public class UsuarioResponse {


    private int id;
    private String email;

    private String firstName;
    private String lastName;

    private String avatar;

    private SupportResponse supportResponse;


    public UsuarioResponse() {
    }

    public UsuarioResponse(int id, String email, String firstName, String lastName, String avatar, SupportResponse supportResponse) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.supportResponse = supportResponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public SupportResponse getSupportResponse() {
        return supportResponse;
    }

    public void setSupportResponse(SupportResponse supportResponse) {
        this.supportResponse = supportResponse;
    }
}