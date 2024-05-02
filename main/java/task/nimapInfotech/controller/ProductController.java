package task.nimapInfotech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import task.nimapInfotech.dto.Product;
import task.nimapInfotech.service.ProductService;
import task.nimapInfotech.util.ResponseStructure;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page) {
        return productService.findAllProducts();
    }


    @PostMapping
    public ResponseEntity<ResponseStructure<Product>> createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}