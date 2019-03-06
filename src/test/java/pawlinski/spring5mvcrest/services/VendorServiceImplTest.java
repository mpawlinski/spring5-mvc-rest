package pawlinski.spring5mvcrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pawlinski.spring5mvcrest.api.v1.mapper.VendorMapper;
import pawlinski.spring5mvcrest.api.v1.model.VendorDTO;
import pawlinski.spring5mvcrest.controllers.v1.VendorController;
import pawlinski.spring5mvcrest.domain.Vendor;
import pawlinski.spring5mvcrest.repositories.VendorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class VendorServiceImplTest {

    public static final String VENDOR_NAME = "Johnson's Exotica";

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    Vendor vendor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);

        vendor = new Vendor();
        vendor.setName(VENDOR_NAME);
    }

    @Test
    public void findVendorByName() {
        //given
        when(vendorRepository.findVendorByName(anyString())).thenReturn(vendor);

        //when
        VendorDTO returnedVendorDTO = vendorService.getVendorByName("Johnson's Exotica");

        //then
        assertNotNull(returnedVendorDTO);
        assertEquals("Johnson's Exotica", returnedVendorDTO.getName());
    }

    @Test
    public void getAllVendors() {
        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();

        //then
        assertNotNull(vendorDTOList);
        assertEquals(3, vendorDTOList.size());
    }

    @Test
    public void getVendorById() {
        //given
        vendor.setId(1L);
        Optional<Vendor> vendorOptional = Optional.of(vendor);

        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        //then
        assertEquals(Long.valueOf(1), vendorDTO.getId());
        assertEquals(VENDOR_NAME, vendorDTO.getName());
    }

    @Test
    public void createNewVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("NewVendor");

        Vendor savedVendor = new Vendor();
        savedVendor.setId(1L);
        savedVendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedVendorDTO.getName());
        assertEquals(VendorController.BASE_URL_VENDORS + "/1", savedVendorDTO.getVendorUrl());
    }

    @Test
    public void updateVendor() {
        //given
        VendorDTO newVendorDTO = new VendorDTO();
        newVendorDTO.setName("HavanasBananas");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(newVendorDTO.getName());
        savedVendor.setId(5L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedVendorDTO = vendorService.updateVendor(5L, newVendorDTO);

        //then
        assertEquals(savedVendorDTO.getName(), savedVendor.getName());
        assertEquals(VendorController.BASE_URL_VENDORS + "/5", savedVendorDTO.getVendorUrl());
    }

    @Test
    public void deleteVendorById() {
        //given
        vendor.setId(1L);

        //when
        vendorRepository.deleteById(1L);

        //then
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}