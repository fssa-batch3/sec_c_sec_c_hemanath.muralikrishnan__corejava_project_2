package com.fssa.agrokart.model;

public class Seller {

	private int id;
	private String name;
	private String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + "]";
	}

}
