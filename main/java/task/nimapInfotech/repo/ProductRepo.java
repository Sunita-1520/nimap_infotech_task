package task.nimapInfotech.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import task.nimapInfotech.dto.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Product findById(long id);

    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    List<Product> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> findByNameContaining(String keyword);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllOrderByPriceAsc();

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> findAllOrderByPriceDesc();
    
    Product findById(Long id);
    
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :id")
    int deleteProductById(Long id);
}