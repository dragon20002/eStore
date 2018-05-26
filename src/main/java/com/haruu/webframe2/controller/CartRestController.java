package com.haruu.webframe2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

	// CREATE
	
	// READ
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable("cartId") int id) {
		Cart cart = cartService.getCartById(id);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setCacheControl("max-age=10");

		return new ResponseEntity<>(cart, httpHeaders, HttpStatus.OK);
	}

	// UPDATE
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<CartItem> addItem(@PathVariable(value="productId") int productId) {
		Product product = productService.getProductById(productId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		Cart cart = user.getCart();
		
		// check if cart item for a given product already exists
		List<CartItem> cartItems = cart.getCartItems();
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);

				int quantity = cartItem.getQuantity() + 1;
				if (quantity > product.getUnitInStock())
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				cartItemService.addCartItem(cartItem);
				
				return new ResponseEntity<>(cartItem, HttpStatus.OK);
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

		return new ResponseEntity<>(cartItem, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cart_items/{productId}/{quantityDiff}", method=RequestMethod.PUT)
	public ResponseEntity<CartItem> updateQuantityOfItem(
			@PathVariable(value="productId") int productId,
			@PathVariable(value="quantityDiff") int quantityDiff) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		Cart cart = user.getCart();
		
		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		Product product = cartItem.getProduct();
		
		int quantity = cartItem.getQuantity() + quantityDiff;
		if (quantity <= 0 || quantity > product.getUnitInStock())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		cartItem.setQuantity(quantity);
		cartItem.setTotalPrice(product.getPrice() * quantity);

		cartItemService.addCartItem(cartItem);

		return new ResponseEntity<>(cartItem, HttpStatus.OK);
	}
	
	// DELETE
	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable("cartId") int id) {
		Cart cart = cartService.getCartById(id);
		cartItemService.deleteAllCartItem(cart);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/cart_items/{productId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteItem(@PathVariable(value="productId") int productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		Cart cart = user.getCart();
		
		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.deleteCartItem(cartItem);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
