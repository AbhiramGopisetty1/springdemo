package com.stackroute.keepnote.service;

import java.util.List;

import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Note;

public interface NoteService {

	public boolean createNote(Note note) throws ReminderNotFoundException, CategoryNotFoundException;

	public boolean deleteNote(int noteId);

	public List<Note> getAllNotesByUserId(String userId);

	public Note getNoteById(int noteId) throws NoteNotFoundException;

	public Note updateNote(Note note, int id) throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException;
}
