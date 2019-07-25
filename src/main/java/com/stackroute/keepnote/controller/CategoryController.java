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
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {

		this.categoryService = categoryService;
	}

	@PostMapping()
	public ResponseEntity<?> createCategory(@RequestBody Category category, HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		String userId = (String) session.getAttribute("loggedInUserId");

		if (userId != null) {

			category.setCategoryCreatedBy(userId);

			if (categoryService.createCategory(category)) {

				responseEntity = new ResponseEntity<Category>(category, HttpStatus.CREATED);

			} else {
				responseEntity = new ResponseEntity<String>("Category cannot be created please try again",
						HttpStatus.CONFLICT);
			}
		} else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable() int id, HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		if ((String) session.getAttribute("loggedInUserId") != null) {

			if (categoryService.deleteCategory(id)) {
				responseEntity = new ResponseEntity<>("Successfully Deleted category", HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("Cannot delete, please try again", HttpStatus.NOT_FOUND);
			}

		} else {

			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable() int id, @RequestBody Category category, HttpSession session)
			throws CategoryNotFoundException {

		ResponseEntity<?> responseEntity = null;
		String userId = (String) session.getAttribute("loggedInUserId");

		if (userId != null) {

			category.setCategoryCreatedBy(userId);

			category = categoryService.updateCategory(category, id);

			if (category != null) {

				responseEntity = new ResponseEntity<Category>(category, HttpStatus.OK);
			} else {

				responseEntity = new ResponseEntity<String>("Cannot update category", HttpStatus.NOT_FOUND);
			}
		} else {
			responseEntity = new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);

		}

		return responseEntity;

	}

	@GetMapping()
	public ResponseEntity<?> getAllCategoriesByUserId(HttpSession session) {

		ResponseEntity<?> responseEntity = null;

		String userId = (String) session.getAttribute("loggedInUserId");

		if (userId != null) {

			responseEntity = new ResponseEntity<List<Category>>(categoryService.getAllCategoryByUserId(userId),
					HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;

	}
}