package task.nimapInfotech.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import task.nimapInfotech.dto.Product;
import task.nimapInfotech.repo.ProductRepo;

@Repository
public class ProductDao {

    @Autowired
    private ProductRepo repo;

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> findAllProducts() {
        return repo.findAll();
    }

    public void deleteById(long id) {
        repo.deleteById((int) id);
    }

    public Product findByName(String name) {
        List<Product> products = repo.findByNameContaining(name);
        if (!products.isEmpty()) {
            return products.get(0);
        } else {
            return null;
        }
    }

    public Product findById(Long id) {
    	if(repo.findById(id)!=null) {
    		return repo.findById(id);
    	}else {
    		return null ;
    	}
    }
    public List<Product> findByCategoryId(Long categoryId) {
        return repo.findByCategoryId(categoryId);
    }
    
    public boolean deleteById(Long id) {
        try {
            repo.deleteById(id);
            return true; 
        } catch (Exception e) {
            return false;
        }
    }
}