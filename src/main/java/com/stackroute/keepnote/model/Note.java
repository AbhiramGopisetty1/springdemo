package com.stackroute.keepnote.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noteId;
	private String noteTitle;
	private String noteContent;
	private String noteStatus;
	private Date createdAt;
	@ManyToOne(fetch = FetchType.EAGER)

	private Category category;
	@ManyToOne(fetch = FetchType.EAGER)

	private Reminder reminder;
	private String createdBy;
	
	
	public Note() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Note(int noteId, String noteTitle, String noteContent, String noteStatus, Date createdAt,
			Category category, Reminder reminder, String createdBy) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.createdAt = createdAt;
		this.category = category;
		this.reminder = reminder;
		this.createdBy = createdBy;
	}



	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	public Date getNoteCreatedAt() {
		return createdAt;
	}

	public void setNoteCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", noteTitle=" + noteTitle + ", noteContent=" + noteContent + ", noteStatus="
				+ noteStatus + ", createdAt=" + createdAt + ", category=" + category + ", reminder="
				+ reminder + ", createdBy=" + createdBy + "]";
	}

}
