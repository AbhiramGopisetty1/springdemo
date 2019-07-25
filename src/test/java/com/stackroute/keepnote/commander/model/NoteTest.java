package com.stackroute.keepnote.commander.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.stackroute.keepnote.model.Note;

public class NoteTest {
	
	private Note note;

	@Before
	public void setUp() throws Exception {
		note = new Note();
		note.setNoteId(1);
		note.setNoteTitle("Testing");
		note.setNoteContent("Bean Testing");
		note.setCreatedBy("Jhon123");
		note.setCategory(null);
		note.setReminder(null);
		note.setNoteStatus("Active");
		note.setNoteCreatedAt(new Date());
	}


	@Test
	public void Beantest() {
		new BeanTester().testBean(Note.class);
		
	}

}
