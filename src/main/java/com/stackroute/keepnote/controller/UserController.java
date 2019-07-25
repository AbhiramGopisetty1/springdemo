package com.stackroute.keepnote.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.keepnote.exception.UserAlreadyExistException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		ResponseEntity<?> responseEntity = null;
		try {

			userService.registerUser(user);
			responseEntity = new ResponseEntity<>("Registartion successful", HttpStatus.CREATED);

		} catch (UserAlreadyExistException e) {
			responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return responseEntity;

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable() String id, @RequestBody User user, HttpSession session)
			throws Exception {

		ResponseEntity<?> responseEntity = null;

		String userId = (String) session.getAttribute("loggedInUserId");
		if (userId != null) {
			user.setUserId(userId);
			user = userService.updateUser(user, id);
			if (user != null) {

				responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
			}

			else {

				responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}

		}

		else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable() String id, HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		if ((String) session.getAttribute("loggedInUserId") != null) {

			boolean status = userService.deleteUser(id);

			if (status) {
				responseEntity = new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
			}

			else {
				responseEntity = new ResponseEntity<String>("Cannot delete User please try again",
						HttpStatus.NOT_FOUND);
			}
		}

		else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByUserId(@PathVariable() String id, HttpSession session) throws UserNotFoundException {

		ResponseEntity<?> responseEntity = null;
		if ((String) session.getAttribute("loggedInUserId") != null) {

			User user = userService.getUserById(id);

			if (user != null) {
				responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
			}

			else {
				responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}

		}

		else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;
	}

}