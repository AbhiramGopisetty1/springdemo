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

import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

	private NoteService noteService;

	@Autowired
	public NoteController(NoteService noteService) {

		this.noteService = noteService;
	}

	// Add New Note

	@PostMapping()
	public ResponseEntity<?> addNote(@RequestBody Note note, HttpSession session)
			throws ReminderNotFoundException, CategoryNotFoundException {

		ResponseEntity<?> responseEntity = null;

		if ((String) session.getAttribute("loggedInUserId") != null) {
			note.setCreatedBy((String) session.getAttribute("loggedInUserId"));

			boolean status = noteService.createNote(note);
			if (status) {

				responseEntity = new ResponseEntity<Note>(note, HttpStatus.CREATED);
			}

			else {
				responseEntity = new ResponseEntity<>("cannot create new note please try again", HttpStatus.CONFLICT);
			}

		}

		else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	// Delete a Note

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable() int id, HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		if ((String) session.getAttribute("loggedInUserId") != null) {

			boolean status = noteService.deleteNote(id);
			if (status) {

				responseEntity = new ResponseEntity<>("Deleted", HttpStatus.OK);
			}

			else {
				responseEntity = new ResponseEntity<>("Deleted", HttpStatus.NOT_FOUND);
			}
		}

		else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	// Update a Note

	@PutMapping("/{id}")
	public ResponseEntity<?> updateNote(@RequestBody Note note, @PathVariable() int id, HttpSession session)
			throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException {

		ResponseEntity<?> responseEntity = null;

		String userId = (String) session.getAttribute("loggedInUserId");
		if ((String) session.getAttribute("loggedInUserId") != null) {
			note.setCreatedBy(userId);

			note = noteService.updateNote(note, id);

			if (note != null) {

				responseEntity = new ResponseEntity<Note>(note, HttpStatus.OK);
			}

			else {

				responseEntity = new ResponseEntity<String>("Cannot update note", HttpStatus.NOT_FOUND);
			}

		}

		else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	@GetMapping()
	public ResponseEntity<?> getAllNotesByUserId(HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		String userId = (String) session.getAttribute("loggedInUserId");

		if (userId != null) {

			responseEntity = new ResponseEntity<List<Note>>(noteService.getAllNotesByUserId(userId), HttpStatus.OK);
		}

		else {
			responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

}
