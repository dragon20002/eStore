package com.haruu.webframe2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.haruu.webframe2.model.Product;

@Repository
//@EnableTransactionManagement //TODO xml의 tx:annotation-driven과 중복되는 곳....오류나면 지울 것!
@Transactional
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Product");

		return query.getResultList();
	}

	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(Product.class, id);
	}

	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}

	public void updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}

	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	}

}
