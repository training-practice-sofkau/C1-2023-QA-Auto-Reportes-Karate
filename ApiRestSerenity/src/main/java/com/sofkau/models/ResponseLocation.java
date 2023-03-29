package com.sofkau.models;

import java.util.List;
import lombok.Data;

@Data
public class ResponseLocation {
	private String created;
	private String name;
	private List<String> residents;
	private int id;
	private String type;
	private String dimension;
	private String url;
}