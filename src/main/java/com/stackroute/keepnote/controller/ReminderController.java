package com.stackroute.keepnote.controller;

import java.util.List;

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
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.ReminderService;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

	private ReminderService reminderService;

	@Autowired
	public ReminderController(ReminderService reminderService) {

		this.reminderService = reminderService;
	}

	@PostMapping()
	public ResponseEntity<?> createReminder(@RequestBody Reminder reminder, HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		String userId = (String) session.getAttribute("loggedInUserId");

		if (userId != null) {

			reminder.setReminderCreatedBy(userId);

			if (reminderService.createReminder(reminder)) {

				responseEntity = new ResponseEntity<Reminder>(reminder, HttpStatus.CREATED);

			} else {
				responseEntity = new ResponseEntity<String>("Reminder cannot be created please try again",
						HttpStatus.CONFLICT);
			}
		} else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReminder(@PathVariable() int id, HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		if ((String) session.getAttribute("loggedInUserId") != null) {

			if (reminderService.deleteReminder(id)) {

				responseEntity = new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("Unable to delete please try again", HttpStatus.NOT_FOUND);
			}
		} else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateReminder(@PathVariable() int id, @RequestBody Reminder reminder, HttpSession session)
			throws ReminderNotFoundException {

		ResponseEntity<?> responseEntity = null;

		String userId = (String) session.getAttribute("loggedInUserId");

		if (userId != null) {

			reminder.setReminderCreatedBy(userId);

			reminder = reminderService.updateReminder(reminder, id);

			if (reminder != null) {

				responseEntity = new ResponseEntity<Reminder>(reminder, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<String>("Unable to update Reminder", HttpStatus.NOT_FOUND);
			}

		} else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	@GetMapping()
	public ResponseEntity<?> getAllRemindersByUserId(HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		String userId = (String) session.getAttribute("loggedInUserId");
		if (userId != null) {

			responseEntity = new ResponseEntity<List<Reminder>>(reminderService.getAllReminderByUserId(userId),
					HttpStatus.OK);

		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getReminderById(@PathVariable() int id, HttpSession session)
			throws ReminderNotFoundException {

		ResponseEntity<?> responseEntity = null;

		if ((String) session.getAttribute("loggedInUserId") != null) {

			Reminder reminder = reminderService.getReminderById(id);
			if (reminder != null) {

				responseEntity = new ResponseEntity<Reminder>(reminder, HttpStatus.OK);

			} else {

				responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}

		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

}