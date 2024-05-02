package task.nimapInfotech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import task.nimapInfotech.dao.CategoryDao;
import task.nimapInfotech.dao.ProductDao;
import task.nimapInfotech.dto.Category;
import task.nimapInfotech.dto.Product;
import task.nimapInfotech.util.ResponseStructure;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private CategoryDao categoryDao;
    
    

    public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
        Product savedProduct = productDao.saveProduct(product);
        ResponseStructure<Product> structure = new ResponseStructure<>();
        structure.setMessage("Product Saved Successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(savedProduct);
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Product>> findProductByName(String name) {
        Product product = productDao.findByName(name);
        ResponseStructure<Product> structure = new ResponseStructure<>();
        if (product != null) {
            structure.setMessage("Product Found Successfully");
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setData(product);
        } else {
            structure.setMessage("Product Not Found");
            structure.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    

    public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
        List<Product> products = productDao.findAllProducts();
        ResponseStructure<List<Product>> structure = new ResponseStructure<>();
        structure.setMessage("All Products Listed Successfully");
        structure.setStatus(HttpStatus.FOUND.value());
        structure.setData(products);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    public ResponseEntity<ResponseStructure<Product>> findProductById(Long id) {
        try {
            Product product = productDao.findById(id);
            if (product != null) {
                // Fetch category details for the product
                Category category = categoryDao.findById(product.getCategory().getId());
                product.setCategory(category);

                return new ResponseEntity<>(new ResponseStructure<>("Product Found Successfully", HttpStatus.FOUND.value(), product), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseStructure<>("Product Not Found", HttpStatus.NOT_FOUND.value(), null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseStructure<>("Error occurred while fetching product", HttpStatus.INTERNAL_SERVER_ERROR.value(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
        Product updatedProduct = productDao.updateProduct(product);
        ResponseStructure<Product> structure = new ResponseStructure<>();
        if (updatedProduct != null) {
            structure.setMessage("Product Updated Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(updatedProduct);
        } else {
            structure.setMessage("Product Not Found");
            structure.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Product>> deleteProductById(Long id) {
        boolean deleted = productDao.deleteById(id);
        ResponseStructure<Product> structure = new ResponseStructure<>();
        if (deleted) {
            structure.setMessage("Product Deleted Successfully");
            structure.setStatus(HttpStatus.OK.value());
        } else {
            structure.setMessage("Product Not Found");
            structure.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
}
