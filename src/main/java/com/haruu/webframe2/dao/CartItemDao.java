package com.haruu.webframe2.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.haruu.webframe2.model.Cart;
import com.haruu.webframe2.model.CartItem;

@Repository
@EnableTransactionManagement // TODO xml의 tx:annotation-driven과 중복되는 곳....오류나면 지울 것!
@Transactional
public class CartItemDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public void addCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush();
	}

	public void deleteCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cartItem);
		session.flush();
	}

	public void deleteAllCartItem(Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();
		//Cart의 List가 Fetch=LAZY면 cartItems에 null값이 들어갈 것이다.

		for (CartItem item : cartItems)
			deleteCartItem(item);
	}

	@SuppressWarnings("unchecked")
	public CartItem getCartItemById(int cartId, int productId) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<CartItem> query = session.createQuery("FROM CartItem WHERE cart.id=? and product.id=?");
		query.setParameter(0, cartId);
		query.setParameter(1, productId);
		return (CartItem) query.getSingleResult();
	}

}
