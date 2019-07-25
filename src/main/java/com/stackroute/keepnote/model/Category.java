package com.stackroute.keepnote.model;


import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String categoryName;
	private String categoryDescription;
	private Date categoryCreationDate;
	private String categoryCreatedBy;
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Note> notes;

	public Category() {

		// TODO Auto-generated constructor stub
	}

	public Category(int categoryId, String categoryName, String categoryDescription, Date categoryCreationDate,
			String categoryCreatedBy, List<Note> notes) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.categoryCreationDate = categoryCreationDate;
		this.categoryCreatedBy = categoryCreatedBy;
		this.notes = notes;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Date getCategoryCreationDate() {
		return categoryCreationDate;
	}

	public void setCategoryCreationDate(Date categoryCreationDate) {
		this.categoryCreationDate = categoryCreationDate;
	}

	public String getCategoryCreatedBy() {
		return categoryCreatedBy;
	}

	public void setCategoryCreatedBy(String categoryCreatedBy) {
		this.categoryCreatedBy = categoryCreatedBy;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + ", categoryCreationDate=" + categoryCreationDate + ", categoryCreatedBy="
				+ categoryCreatedBy + "]";
	}

}