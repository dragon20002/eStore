package com.haruu.webframe2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haruu.webframe2.model.Product;
import com.haruu.webframe2.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);

		return "products";
	}

	@RequestMapping("/viewProduct/{productId}")
	public String getProducts(@PathVariable int productId, Model model) {
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);

		return "viewProduct";
	}
	
}
