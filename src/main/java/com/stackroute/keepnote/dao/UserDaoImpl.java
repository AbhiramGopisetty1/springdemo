package com.stackroute.keepnote.dao;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean registerUser(User user) {

		sessionFactory.getCurrentSession().save(user);

		return true;
	}

	@Override
	public boolean updateUser(User user) {

		sessionFactory.getCurrentSession().update(user);
		return true;

	}

	@Override
	public User getUserById(String userId) {

		User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);

		Hibernate.initialize(user);
		return user;
	}

	@Override
	public boolean validateUser(String userId, String password) throws UserNotFoundException {
		
		User u1 = (User) sessionFactory.getCurrentSession().createQuery("from User where userId = ? and userPassword = ?")
				.setParameter(0, userId).setParameter(1, password).uniqueResult();

		if (u1 != null) {
			return true;
		} else {
			throw new UserNotFoundException("User with Id does not exsist");
		}
	}

	@Override
	public boolean deleteUser(String userId) {

		try {
			User user = sessionFactory.getCurrentSession().get(User.class, userId);
			sessionFactory.getCurrentSession().delete(user);
			sessionFactory.getCurrentSession().flush();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
