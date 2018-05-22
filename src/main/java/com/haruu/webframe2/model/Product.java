package com.haruu.webframe2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = 6676626852682696735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int id;

	@NotEmpty(message = "The name is not null")
	private String name;

	private String category;

	@Min(value = 0, message = "The product price is not less than 0")
	private int price;

	@NotEmpty(message = "The manufacturer is not null")
	private String manufacturer;

	@Min(value = 0, message = "The product stock is not less than 0")
	private int unitInStock;

	private String description;

	@Transient
	private MultipartFile productImage;
	private String imageFilename;
}
