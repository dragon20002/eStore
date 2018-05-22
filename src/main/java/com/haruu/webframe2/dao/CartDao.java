package com.haruu.webframe2.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.haruu.webframe2.model.Cart;

@Repository
@EnableTransactionManagement //TODO xml의 tx:annotation-driven과 중복되는 곳....오류나면 지울 것!
@Transactional
public class CartDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Cart getCartById(int cartId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(Cart.class, cartId);
	}

}
