package pawlinski.spring5mvcrest.api.v1.model;

import lombok.Data;

@Data
public class VendorDTO {

    private Long id;
    private String name;
    private String vendorUrl;
}
