package pawlinski.spring5mvcrest.services;

import org.springframework.stereotype.Service;
import pawlinski.spring5mvcrest.api.v1.mapper.VendorMapper;
import pawlinski.spring5mvcrest.api.v1.model.VendorDTO;
import pawlinski.spring5mvcrest.controllers.v1.VendorController;
import pawlinski.spring5mvcrest.domain.Vendor;
import pawlinski.spring5mvcrest.exceptions.ResourceNotFoundException;
import pawlinski.spring5mvcrest.repositories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorDTO getVendorByName(String name) {
        return vendorMapper.vendorToVendorDto(vendorRepository.findVendorByName(name));
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);
                    vendorDTO.setVendorUrl(VendorController.BASE_URL_VENDORS + "/" + vendor.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);
                    vendorDTO.setVendorUrl(VendorController.BASE_URL_VENDORS + "/" + vendor.getId());

                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor savedVendor = vendorRepository.save(vendorMapper.vendorDtoToVendor(vendorDTO));

        VendorDTO returnedVendorDTO = vendorMapper.vendorToVendorDto(savedVendor);
        returnedVendorDTO.setVendorUrl(VendorController.BASE_URL_VENDORS + "/" + savedVendor.getId());

        return returnedVendorDTO;
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor newVendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        newVendor.setId(id);

        Vendor savedVendor = vendorRepository.save(newVendor);

        VendorDTO returnedVendorDTO = vendorMapper.vendorToVendorDto(savedVendor);
        returnedVendorDTO.setVendorUrl(VendorController.BASE_URL_VENDORS + "/" + savedVendor.getId());

        return returnedVendorDTO;
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }

                    VendorDTO returnedVendorDTO = vendorMapper.vendorToVendorDto(vendorRepository.save(vendor));
                    returnedVendorDTO.setVendorUrl(VendorController.BASE_URL_VENDORS + "/" + vendor.getId());

                    return returnedVendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
