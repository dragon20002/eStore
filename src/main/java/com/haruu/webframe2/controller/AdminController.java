package com.haruu.webframe2.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

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
		model.addAttribute(new Product());
		return "addProduct";
	}
	
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.POST)
	public String addProduct(
			HttpServletRequest request,
			@Valid @ModelAttribute("product") Product product,
			BindingResult rs) {
		if (rs.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = rs.getAllErrors();
			
			for (ObjectError error : errors)
				System.out.println(error.getDefaultMessage());
			
			return "addProduct";
		}

		MultipartFile productImage = product.getProductImage();
		String rootDir = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDir + "\\resources\\images\\" + productImage.getOriginalFilename());
		if (productImage.isEmpty() == false) {
			System.out.println("-------- file start ---------");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("-----------------------------");
		}
		
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
				product.setImageFilename(productImage.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		productService.addProduct(product);
		
		return "redirect:/admin/productInventory";	// 다시 getProducts Method를 호출하여 Model을 새로 만들어 전달한다
		//return "productInventory					// 최근 추가한 내용이 추가되지 않은 Model이 전달된다
	}

	@RequestMapping(value="/productInventory/deleteProduct/{id}", method=RequestMethod.GET)
	public String deleteProduct(HttpServletRequest request, @PathVariable int id) {
		Product product = productService.getProductById(id);

		// delete thumbnail
		String rootDir = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDir + "\\resources\\images\\" + product.getImageFilename());
		if (Files.exists(savePath)) {
			try {
				Files.delete(savePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		productService.deleteProduct(product);

		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value="/productInventory/updateProduct/{id}", method=RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		
		return "updateProduct";
	}
	
	@RequestMapping(value="/productInventory/update", method=RequestMethod.POST)
	public String updateUsingPost(
		HttpServletRequest request,	
		@ModelAttribute("product") Product product) {
		productService.updateProduct(product);

		MultipartFile productImage = product.getProductImage();
		String rootDir = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDir + "\\resources\\images\\" + productImage.getOriginalFilename());
		if (productImage.isEmpty() == false) {
			System.out.println("-------- file start ---------");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("-----------------------------");
		}
		
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
				product.setImageFilename(productImage.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/admin/productInventory";	// 다시 getProducts Method를 호출하여 Model을 새로 만들어 전달한다
	}
}
