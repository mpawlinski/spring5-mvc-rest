package pawlinski.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pawlinski.spring5mvcrest.api.v1.model.CategoryDTO;
import pawlinski.spring5mvcrest.domain.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

//    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDto(Category category);
}
