package pawlinski.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pawlinski.spring5mvcrest.api.v1.model.CategoryDTO;
import pawlinski.spring5mvcrest.api.v1.model.CategoryListDTO;
import pawlinski.spring5mvcrest.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {

        return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {

        return new ResponseEntity<>(new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK );
    }
}
