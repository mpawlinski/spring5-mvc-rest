package pawlinski.spring5mvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;

    @ApiModelProperty(value = "Customer's first name", required = true)
    private String firstName;

    @ApiModelProperty(value = "Customer's last name", required = true)
    private String lastName;

    @ApiModelProperty(value = "Customer's url", required = true)
    private String customerUrl;
}
