package com.stackroute.keepnote.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.keepnote.dao.UserDAO;
import com.stackroute.keepnote.exception.UserAlreadyExistException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {

		this.userDAO = userDAO;
	}

	@Override
	public boolean registerUser(User user) throws UserAlreadyExistException {

		User u1 = userDAO.getUserById(user.getUserId());

		if (u1 == null) {

			user.setUserAddedDate(new Date());
			userDAO.registerUser(user);
			return true;

		}

		else {
			throw new UserAlreadyExistException("User with id" + user.getUserId()
					+ "already exists in our system please choose other Id for yourself");

		}

	}

	@Override
	public User updateUser(User user, String userId) throws Exception {

		User updatedUser = userDAO.getUserById(userId);

		if (updatedUser != null) {
			updatedUser.setUserName(user.getUserName());
			updatedUser.setUserMobile(user.getUserMobile());
			updatedUser.setUserAddedDate(new Date());
			updatedUser.setUserPassword(user.getUserPassword());
			userDAO.updateUser(updatedUser);

			return updatedUser;

		}

		else {
			throw new Exception("Unable to update details for " + user.getUserId() + "please try again");
		}

	}

	@Override
	public User getUserById(String UserId) throws UserNotFoundException {

		User user = userDAO.getUserById(UserId);

		if (user != null) {

			return user;
		}

		else {
			throw new UserNotFoundException("User with Id does not exists");
		}
	}

	@Override
	public boolean validateUser(String userId, String password) throws UserNotFoundException {

		boolean status = userDAO.validateUser(userId, password);

		if (status) {

			return true;
		}

		else {

			throw new UserNotFoundException("userId and password does not match");
		}

	}

	@Override
	public boolean deleteUser(String UserId) {

		return userDAO.deleteUser(UserId);
	}

}
