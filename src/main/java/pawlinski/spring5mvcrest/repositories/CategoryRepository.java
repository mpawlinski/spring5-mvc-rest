package pawlinski.spring5mvcrest.repositories;

import org.springframework.data.repository.CrudRepository;
import pawlinski.spring5mvcrest.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
