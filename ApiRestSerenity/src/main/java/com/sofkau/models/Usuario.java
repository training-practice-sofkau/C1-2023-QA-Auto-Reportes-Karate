package com.sofkau.models;

import lombok.Data;

public class Usuario{

	private String lastName;
	private int id;
	private String avatar;
	private String firstName;
	private String email;
	private Support support;


	public Usuario(String lastName, int id, String avatar, String firstName, String email, Support support) {
		this.lastName = lastName;
		this.id = id;
		this.avatar = avatar;
		this.firstName = firstName;
		this.email = email;
		this.support = support;
	}


	public Usuario() {
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Support getSupport() {
		return support;
	}

	public void setSupport(Support support) {
		this.support = support;
	}



}