package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.UserAlreadyExistException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

public interface UserService {

	public boolean registerUser(User user) throws UserAlreadyExistException;

	public User updateUser(User user, String id) throws Exception;

	public boolean deleteUser(String UserId);

	public boolean validateUser(String userName, String password) throws UserNotFoundException;

	public User getUserById(String userId) throws UserNotFoundException;

}
