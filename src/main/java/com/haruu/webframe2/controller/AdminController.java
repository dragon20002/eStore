package com.haruu.webframe2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haruu.webframe2.model.Product;
import com.haruu.webframe2.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/home")
	public String adminPage() {
		return "admin";
	}

	@RequestMapping("/productInventory")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);

		return "productInventory";
	}

	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		product.setCategory("S/W");
		model.addAttribute("product", product);

		return "addProduct";
	}
	
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.POST)
	public String addProductUsingPost(@Valid @ModelAttribute("product") Product product, BindingResult rs) {
		if (rs.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = rs.getAllErrors();
			
			for (ObjectError error : errors)
				System.out.println(error.getDefaultMessage());
			
			return "addProduct";
		}
		
		if (productService.addProduct(product))
			System.out.println("Adding product cannot be done...");
		
		return "redirect:/admin/productInventory";	// 다시 getProducts Method를 호출하여 Model을 새로 만들어 전달한다
		//return "productInventory					// 최근 추가한 내용이 추가되지 않은 Model이 전달된다
	}

	@RequestMapping(value="/productInventory/deleteProduct/{id}", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable int id) {
		if (!productService.deleteProduct(id))
			System.out.println("Deleting product cannot be done...");
		
		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value="/productInventory/updateProduct/{id}", method=RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		
		return "updateProduct";
	}
	
	@RequestMapping(value="/productInventory/update", method=RequestMethod.POST)
	public String updateUsingPost(@ModelAttribute("product") Product product) {
		if (!productService.updateProduct(product))
			System.out.println("Updating product cannot be done...");

		return "redirect:/admin/productInventory";	// 다시 getProducts Method를 호출하여 Model을 새로 만들어 전달한다
	}
}
