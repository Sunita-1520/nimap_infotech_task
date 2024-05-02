package task.nimapInfotech.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import task.nimapInfotech.dto.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Category findById(Long id);

	Category findByName(String name);
}
