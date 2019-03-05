package pawlinski.spring5mvcrest.controllers.v1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pawlinski.spring5mvcrest.api.v1.model.CategoryDTO;
import pawlinski.spring5mvcrest.services.CategoryService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    public static final String NAME = "Fruits";

    MockMvc mockMvc;

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        //categoryController = new CategoryController(categoryService);
    }

    @Test
    public void getCategoryByName() throws Exception{
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(1L);
        categoryDto.setName(NAME);

        when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDto);

        mockMvc.perform(get(CategoryController.BASE_CATEGORY_URL + "Fruits").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void getAllCategories() throws Exception {
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(1L);
        categoryDTO1.setName(NAME);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId(2L);
        categoryDTO2.setName("Dried");

        List<CategoryDTO> list = Arrays.asList(categoryDTO1, categoryDTO2);

        when(categoryService.getAllCategories()).thenReturn(list);

        mockMvc.perform(get(CategoryController.BASE_CATEGORY_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }
}