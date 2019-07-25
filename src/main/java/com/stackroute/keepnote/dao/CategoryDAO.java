package com.stackroute.keepnote.dao;

import java.util.List;

import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;

public interface CategoryDAO {

	public boolean createCategory(Category category);

	public boolean deleteCategory(int noteId);

	public boolean updateCategory(Category category);

	public Category getCategoryById(int categoryId) throws CategoryNotFoundException;

	public List<Category> getAllCategoryByUserId(String userId);
}
