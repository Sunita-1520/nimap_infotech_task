package task.nimapInfotech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import task.nimapInfotech.dao.CategoryDao;
import task.nimapInfotech.dto.Category;
import task.nimapInfotech.util.ResponseStructure;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public ResponseEntity<ResponseStructure<Category>> saveCategory(Category category) {
		Category savedCategory = categoryDao.saveCategory(category);
		ResponseStructure<Category> structure = new ResponseStructure<>();
		structure.setMessage("Category Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(savedCategory);
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Category>> findCategoryByName(String name) {
		Category category = categoryDao.findByName(name);
		ResponseStructure<Category> structure = new ResponseStructure<>();
		if (category != null) {
			structure.setMessage("Category Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(category);
		} else {
			structure.setMessage("Category Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Category>>> findAllCategories() {
		List<Category> categories = categoryDao.findAllCategories();
		ResponseStructure<List<Category>> structure = new ResponseStructure<>();
		structure.setMessage("All Categories Listed Successfully");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(categories);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Category>> updateCategory(Category category) {
		Category updatedCategory = categoryDao.saveCategory(category);
		ResponseStructure<Category> structure = new ResponseStructure<>();
		if (updatedCategory != null) {
			structure.setMessage("Category Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(updatedCategory);
		} else {
			structure.setMessage("Category Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Category>> deleteCategoryById(int id) {
		boolean deleted = categoryDao.deleteById(id);
		ResponseStructure<Category> structure = new ResponseStructure<>();
		if (deleted) {
			structure.setMessage("Category Deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
		} else {
			structure.setMessage("Category Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
		}

		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
}
