package com.haruu.webframe2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haruu.webframe2.model.Cart;
import com.haruu.webframe2.model.CartItem;
import com.haruu.webframe2.model.Product;
import com.haruu.webframe2.model.User;
import com.haruu.webframe2.service.CartItemService;
import com.haruu.webframe2.service.CartService;
import com.haruu.webframe2.service.ProductService;
import com.haruu.webframe2.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

	@Autowired
	CartService cartService;

	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable("cartId") int id) {
		Cart cart = cartService.getCartById(id);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable("cartId") int id) {
		Cart cart = cartService.getCartById(id);
		cartItemService.deleteAllCartItem(cart);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value="productId") int productId) {
		Product product = productService.getProductById(productId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		// check if cart item for a given product already exists
		List<CartItem> cartItems = cart.getCartItems();
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				cartItemService.addCartItem(cartItem);
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		// create new cart item
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice());
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		
		// bidirectional
		cart.getCartItems().add(cartItem);
		cartItemService.addCartItem(cartItem);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/cartitem/{productId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteItem(@PathVariable(value="productId") int productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByUsername(username);
		Cart cart = user.getCart();
		
		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.deleteCartItem(cartItem);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
