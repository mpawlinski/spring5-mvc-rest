package pawlinski.spring5mvcrest.api.v1.mapper;

import org.junit.Test;
import pawlinski.spring5mvcrest.api.v1.model.CategoryDTO;
import pawlinski.spring5mvcrest.domain.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryMapperTest {

    public static final long ID = 1L;
    public static final String FRUITS = "Fruits";

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void testMapping() {
        //given
        Category fruits = new Category();
        fruits.setId(ID);
        fruits.setName(FRUITS);

        //when
        CategoryDTO fruitsDto = categoryMapper.categoryToCategoryDto(fruits);

        //then
        assertNotNull(fruitsDto);
        assertEquals(Long.valueOf(ID), fruitsDto.getId());
        assertEquals(FRUITS, fruitsDto.getName());
    }

}