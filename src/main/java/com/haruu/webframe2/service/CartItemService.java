package com.haruu.webframe2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haruu.webframe2.dao.CartItemDao;
import com.haruu.webframe2.model.Cart;
import com.haruu.webframe2.model.CartItem;

@Service
public class CartItemService {

	@Autowired
	private CartItemDao cartItemDao;

	public void addCartItem(CartItem cartItem) {
		cartItemDao.addCartItem(cartItem);
	}

	public void deleteCartItem(CartItem cartItem) {
		cartItemDao.deleteCartItem(cartItem);
	}
	
	public void deleteAllCartItem(Cart cart) {
		cartItemDao.deleteAllCartItem(cart);
	}

	public CartItem getCartItemByProductId(int cartId, int productId) {
		return cartItemDao.getCartItemById(cartId, productId);
	}

}
