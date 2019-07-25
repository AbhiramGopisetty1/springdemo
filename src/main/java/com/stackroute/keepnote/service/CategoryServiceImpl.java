package com.stackroute.keepnote.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.CategoryDAO;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryDAO categoryDAO;

	@Autowired
	public CategoryServiceImpl(CategoryDAO categoryDAO) {

		this.categoryDAO = categoryDAO;
	}

	@Override
	public boolean createCategory(Category category) {
		boolean status = categoryDAO.createCategory(category);
		return status;

	}

	@Override
	public boolean deleteCategory(int categoryId) {
		boolean status = categoryDAO.deleteCategory(categoryId);
		return status;
	}

	@Override
	public Category updateCategory(Category category, int id) throws CategoryNotFoundException {

		Category updateCategory = categoryDAO.getCategoryById(id);
		
		if(updateCategory != null) {
			

			updateCategory.setCategoryName(category.getCategoryName());
			updateCategory.setCategoryDescription(category.getCategoryDescription());
			updateCategory.setCategoryCreatedBy(category.getCategoryCreatedBy());
			updateCategory.setCategoryCreationDate(new Date());

			categoryDAO.updateCategory(updateCategory);
			return updateCategory;
		}
		
		else {
			
			throw new CategoryNotFoundException("Category Not present");
		}

	}

	@Override
	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
		
		Category fetchedCategory = categoryDAO.getCategoryById(categoryId);
		if(fetchedCategory != null) {
			return fetchedCategory;
		}
		else {
			throw new CategoryNotFoundException("Category does not exists");
		}
	}

	@Override
	public List<Category> getAllCategoryByUserId(String userId) {

		return categoryDAO.getAllCategoryByUserId(userId);
	}

}
