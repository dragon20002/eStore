package com.haruu.webframe2.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Product {
	private int id;
	@NotEmpty(message="The name is not null")
	private String name;
	private String category;
	@Min(value=0, message="The product price is not less than 0")
	private int price;
	@NotEmpty(message="The manufacturer is not null")
	private String manufacturer;
	@Min(value=0, message="The product stock is not less than 0")
	private int unitInStock;
	private String description;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getUnitInStock() {
		return unitInStock;
	}

	public void setUnitInStock(int unitInStock) {
		this.unitInStock = unitInStock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
