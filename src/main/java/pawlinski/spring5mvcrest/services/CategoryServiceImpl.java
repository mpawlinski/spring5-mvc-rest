package pawlinski.spring5mvcrest.services;

import org.springframework.stereotype.Service;
import pawlinski.spring5mvcrest.api.v1.mapper.CategoryMapper;
import pawlinski.spring5mvcrest.api.v1.model.CategoryDTO;
import pawlinski.spring5mvcrest.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String categoryName) {

        return categoryMapper.categoryToCategoryDto(categoryRepository.findByName(categoryName));
    }
}
