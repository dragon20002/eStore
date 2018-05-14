package com.haruu.webframe2.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.haruu.webframe2.model.User;

@Repository
@EnableTransactionManagement // TODO xml의 tx:annotation-driven과 중복되는 곳....오류나면 지울 것!
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.createQuery("from User where username=?");
		query.setParameter(0, username);

		return query.getSingleResult();
	}

	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		user.setPassword(passwordEncoder.encode(user.getPassword())); //pw hashing
		session.saveOrUpdate(user);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.createQuery("from User");
		return query.getResultList();
	}

}