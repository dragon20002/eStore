package com.haruu.webframe2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haruu.webframe2.model.Address;
import com.haruu.webframe2.model.Cart;
import com.haruu.webframe2.model.User;
import com.haruu.webframe2.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerUser(Model model) {
		User user = new User();
		Address address = new Address();

		user.setAddress(address);
		model.addAttribute(user);

		return "register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors())
			return "register";
		
		// check isDuplicated
		List<User> userList = userService.getUsers();
		for (User mUser : userList) {
			if (user.getUsername().equals(mUser.getUsername())) {
				model.addAttribute("usernameMsg", "username alread exist");
				return "register";
			}
		}

		user.setEnabled(true);
		
		// set Authority
		if (user.getUsername().equals("admin"))
			user.setAuthority("ROLE_ADMIN");
		else
			user.setAuthority("ROLE_USER");

		// persist new user
		user.setCart(new Cart());
		userService.addUser(user);

		return "registerSuccess";
	}

}
