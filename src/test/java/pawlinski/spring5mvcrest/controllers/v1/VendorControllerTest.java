package pawlinski.spring5mvcrest.controllers.v1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pawlinski.spring5mvcrest.api.v1.model.VendorDTO;
import pawlinski.spring5mvcrest.services.VendorService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest extends AbstractRestController{

    public static final String VENDOR_NAME = "Exotica Fruitica";
    public static final Long ID = 1L;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Mock
    VendorService vendorService;

    VendorDTO newVendorDTO;

    VendorDTO returnedVendorDTO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();

        newVendorDTO = new VendorDTO();
        newVendorDTO.setName(VENDOR_NAME);
        newVendorDTO.setVendorUrl(VendorController.BASE_URL_VENDORS + "/1");

        returnedVendorDTO = new VendorDTO();
        returnedVendorDTO.setName(newVendorDTO.getName());
        returnedVendorDTO.setVendorUrl(VendorController.BASE_URL_VENDORS + "/" + ID);
    }

    @Test
    public void getAllVendors() throws Exception {
        List<VendorDTO> vendorsDTOList = Arrays.asList(newVendorDTO, new VendorDTO(), new VendorDTO());

        when(vendorService.getAllVendors()).thenReturn(vendorsDTOList);

        mockMvc.perform(get(VendorController.BASE_URL_VENDORS).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(3)));
    }

    @Test
    public void getVendorByName() throws Exception {
        when(vendorService.getVendorByName(anyString())).thenReturn(newVendorDTO);

        mockMvc.perform(get(VendorController.BASE_URL_VENDORS + "/name/" + VENDOR_NAME).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(VENDOR_NAME)));
    }

    @Test
    public void getVendorById() throws Exception {
        when(vendorService.getVendorById(anyLong())).thenReturn(newVendorDTO);

        mockMvc.perform(get(VendorController.BASE_URL_VENDORS + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(VENDOR_NAME)));
    }

    @Test
    public void createNewVendor() throws Exception {
        when(vendorService.createNewVendor(newVendorDTO)).thenReturn(returnedVendorDTO);

        mockMvc.perform(post(VendorController.BASE_URL_VENDORS).contentType(MediaType.APPLICATION_JSON).content(asJsonString(newVendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(VENDOR_NAME)))
                .andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL_VENDORS + "/" + ID)));
    }

    @Test
    public void updateVendor() throws Exception {
        when(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnedVendorDTO);

        mockMvc.perform(put(VendorController.BASE_URL_VENDORS + "/" + ID).contentType(MediaType.APPLICATION_JSON).content(asJsonString(newVendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(VENDOR_NAME)))
                .andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL_VENDORS + "/" + ID)));
    }
}