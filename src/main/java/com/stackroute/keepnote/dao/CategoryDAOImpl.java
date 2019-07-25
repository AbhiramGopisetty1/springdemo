package com.stackroute.keepnote.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public CategoryDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean createCategory(Category category) {
		category.setCategoryCreationDate(new Date());
		sessionFactory.getCurrentSession().save(category);
		return true;
	}

	@Override
	public boolean deleteCategory(int categoryId) {

		try {
			Category category = sessionFactory.getCurrentSession().get(Category.class, categoryId);
			sessionFactory.getCurrentSession().delete(category);
			sessionFactory.getCurrentSession().flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateCategory(Category category) {
		sessionFactory.getCurrentSession().update(category);
		return true;
	}

	@Override
	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {

		Category category = sessionFactory.getCurrentSession().get(Category.class, categoryId);
		if(category != null) {
			
			return category;
		}
		
		else {
			throw new CategoryNotFoundException("Category with id "+ categoryId + "does not exist");
		}
		
	}

	@Override
	public List<Category> getAllCategoryByUserId(String userId) {

		List allCategory = sessionFactory.getCurrentSession().createQuery(
				"from Category where categoryCreatedBy= " + "'" + userId + "'" + "order by categoryCreationDate asc")
				.list();
		return allCategory;
	}

}
