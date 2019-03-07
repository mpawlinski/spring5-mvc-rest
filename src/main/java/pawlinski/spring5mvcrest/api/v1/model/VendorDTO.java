package pawlinski.spring5mvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    private Long id;

    @ApiModelProperty(value = "Vendor's name", required = true)
    private String name;

    @ApiModelProperty(value = "Vendor's url", required = true)
    private String vendorUrl;
}
