package pawlinski.spring5mvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;

    @ApiModelProperty(value = "Category name", required = true)
    private String name;
}
