package pawlinski.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pawlinski.spring5mvcrest.api.v1.model.VendorDTO;
import pawlinski.spring5mvcrest.api.v1.model.VendorListDTO;
import pawlinski.spring5mvcrest.services.VendorService;

@RestController
@RequestMapping(VendorController.BASE_URL_VENDORS)
public class VendorController {

    public static final String BASE_URL_VENDORS = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{vendorName}")
    public VendorDTO getVendorByName(@PathVariable String vendorName) {
        return vendorService.getVendorByName(vendorName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteVendorById(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.updateVendor(id, vendorDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }
}
