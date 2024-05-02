package task.nimapInfotech.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import task.nimapInfotech.dto.Category;
import task.nimapInfotech.repo.CategoryRepo;

@Repository
public class CategoryDao {

    @Autowired
    private CategoryRepo repo;

    public Category saveCategory(Category category) {
        return repo.save(category);
    }

    public List<Category> findAllCategories() {
        return repo.findAll();
    }
    public void deleteById(int id) {
        repo.deleteById(id);
    }
    public Category findByName(String name) {
        return repo.findByName(name);
    }

    public Category findById(Long id) {
    	if(repo.findById(id) !=null) {
    		return repo.findById(id);
    	}else {
    		return null;
    		
    	}
    }
}