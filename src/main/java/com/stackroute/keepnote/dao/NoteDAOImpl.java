package com.stackroute.keepnote.dao;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.model.Note;

@Repository
@Transactional
public class NoteDAOImpl implements NoteDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public NoteDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean createNote(Note note) {
		try {

			note.setNoteCreatedAt(new Date());
			sessionFactory.getCurrentSession().save(note);

			return true;
		} catch (Exception exception) {

			exception.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteNote(int noteId) {

		try {

			Note note = (Note) sessionFactory.getCurrentSession().load(Note.class, noteId);
			sessionFactory.getCurrentSession().delete(note);
			sessionFactory.getCurrentSession().flush();
			return true;

		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getAllNotesByUserId(String userId) {
		List<Note> allNotes = sessionFactory.getCurrentSession()

				.createQuery("from Note where createdBy=" + "'" + userId + "'" + " order by createdAt desc")

				.list();

		return allNotes;
	}

	@Override
	public Note getNoteById(int noteId) throws NoteNotFoundException {
		Note note = sessionFactory.getCurrentSession().get(Note.class, noteId);
		Hibernate.initialize(note);
		if (note != null) {
			return note;
		}
		throw new NoteNotFoundException("Note does not exists");
	}

	@Override
	public boolean UpdateNote(Note note) {
		note.setNoteCreatedAt(new Date());
		sessionFactory.getCurrentSession().update(note);
		return true;
	}

}
