package pawlinski.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawlinski.spring5mvcrest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String categoryName);
}
