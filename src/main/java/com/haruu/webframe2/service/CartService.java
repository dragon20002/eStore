package com.haruu.webframe2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haruu.webframe2.dao.CartDao;
import com.haruu.webframe2.model.Cart;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	public Cart getCartById(int cartId) {
		return cartDao.getCartById(cartId);
	}

}
