package com.stackroute.keepnote.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.keepnote.dao.CategoryDAO;
import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.dao.ReminderDAO;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.Reminder;

@Service
public class NoteServiceImpl implements NoteService {

	private NoteDAO noteDAO;

	private CategoryDAO categoryDAO;

	private ReminderDAO reminderDAO;

	@Autowired
	public NoteServiceImpl(NoteDAO noteDAO, CategoryDAO categoryDAO, ReminderDAO reminderDAO) {

		this.noteDAO = noteDAO;
		this.categoryDAO = categoryDAO;
		this.reminderDAO = reminderDAO;
	}

	@Override
	public boolean createNote(Note note) throws ReminderNotFoundException, CategoryNotFoundException {

		Category category = null;
		Reminder reminder = null;

		if (note.getCategory() != null && note.getReminder() != null) {

			category = categoryDAO.getCategoryById(note.getCategory().getCategoryId());
			
			reminder = reminderDAO.getReminderById(note.getReminder().getReminderId());
			note.setCategory(category);
			note.setReminder(reminder);
		}

		if (note.getCategory() != null && note.getReminder() == null) {

			category = categoryDAO.getCategoryById(note.getCategory().getCategoryId());
			note.setCategory(category);
			note.setReminder(reminder);
		}

		if (note.getCategory() == null && note.getReminder() != null) {

			reminder = reminderDAO.getReminderById(note.getReminder().getReminderId());
			note.setCategory(category);
			note.setReminder(reminder);
		}

		else {

			note.setCategory(category);
			note.setReminder(reminder);
		}

		boolean status = noteDAO.createNote(note);
		
		return status;
	}

	@Override
	public boolean deleteNote(int noteId) {
		boolean status = noteDAO.deleteNote(noteId);
		return status;
	}

	@Override
	public List<Note> getAllNotesByUserId(String userId) {
		return noteDAO.getAllNotesByUserId(userId);
	}

	@Override
	public Note getNoteById(int noteId) throws NoteNotFoundException {

		return noteDAO.getNoteById(noteId);
	}

	@Override
	public Note updateNote(Note note, int id) throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException {

		Note updateNote = noteDAO.getNoteById(id);
		Category category = null;
		Reminder reminder = null;
		if (note.getCategory() != null && note.getReminder() != null) {

			category = categoryDAO.getCategoryById(note.getCategory().getCategoryId());
			reminder = reminderDAO.getReminderById(note.getReminder().getReminderId());
			updateNote.setNoteContent(note.getNoteContent());
			updateNote.setNoteTitle(note.getNoteTitle());
			updateNote.setCategory(category);
			updateNote.setReminder(reminder);
			updateNote.setCreatedBy(note.getCreatedBy());
			updateNote.setNoteStatus(note.getNoteStatus());
		}

		if (note.getCategory() != null && note.getReminder() == null) {

			
			category = categoryDAO.getCategoryById(note.getCategory().getCategoryId());

			updateNote.setNoteContent(note.getNoteContent());
			updateNote.setNoteTitle(note.getNoteTitle());
			updateNote.setCategory(category);
			updateNote.setReminder(reminder);
			updateNote.setCreatedBy(note.getCreatedBy());
			updateNote.setNoteStatus(note.getNoteStatus());
		}

		if (note.getCategory() == null && note.getReminder() != null) {

			reminder = reminderDAO.getReminderById(note.getReminder().getReminderId());
			updateNote.setNoteContent(note.getNoteContent());
			updateNote.setNoteTitle(note.getNoteTitle());
			updateNote.setCategory(category);
			updateNote.setReminder(reminder);
			updateNote.setCreatedBy(note.getCreatedBy());
			updateNote.setNoteStatus(note.getNoteStatus());
		}

		else {

			updateNote.setNoteContent(note.getNoteContent());
			updateNote.setNoteTitle(note.getNoteTitle());
			updateNote.setCategory(category);
			updateNote.setReminder(reminder);
			updateNote.setCreatedBy(note.getCreatedBy());
			updateNote.setNoteStatus(note.getNoteStatus());
		}

		boolean status = noteDAO.UpdateNote(updateNote);
		return updateNote;
	}

}
