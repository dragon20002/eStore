package com.haruu.webframe2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haruu.webframe2.dao.UserDao;
import com.haruu.webframe2.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

}
