package pawlinski.spring5mvcrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pawlinski.spring5mvcrest.api.v1.mapper.CategoryMapper;
import pawlinski.spring5mvcrest.api.v1.model.CategoryDTO;
import pawlinski.spring5mvcrest.domain.Category;
import pawlinski.spring5mvcrest.repositories.CategoryRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    public static final String NAME = "Fruits";
    public static final Long ID = 1L;

    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() {
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoriesDto = categoryService.getAllCategories();

        //then
        assertEquals(3, categoriesDto.size());
    }

    @Test
    public void getCategoryByName() {
        //given
        Category fruits = new Category();
        fruits.setId(ID);
        fruits.setName(NAME);

        when(categoryRepository.findByLastName(anyString())).thenReturn(fruits);

        //when
        CategoryDTO fruitsDto = categoryService.getCategoryByName(NAME);

        //then
        assertNotNull(fruitsDto);
        assertEquals(Long.valueOf(ID), fruitsDto.getId());
        assertEquals(NAME, fruitsDto.getName());
    }
}