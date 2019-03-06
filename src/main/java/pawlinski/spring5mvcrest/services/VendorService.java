package pawlinski.spring5mvcrest.services;

import pawlinski.spring5mvcrest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    VendorDTO getVendorByName(String name);

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
