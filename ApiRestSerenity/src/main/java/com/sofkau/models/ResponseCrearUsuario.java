package com.sofkau.models;

public class ResponseCrearUsuario {
	private String createdAt;
	private String name;
	private String id;
	private String job;

	public ResponseCrearUsuario(String createdAt, String name, String id, String job) {
		this.createdAt = createdAt;
		this.name = name;
		this.id = id;
		this.job = job;
	}

	public ResponseCrearUsuario() {
	}


	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
}