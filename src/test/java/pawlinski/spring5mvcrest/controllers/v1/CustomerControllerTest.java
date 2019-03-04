package pawlinski.spring5mvcrest.controllers.v1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pawlinski.spring5mvcrest.api.v1.model.CustomerDTO;
import pawlinski.spring5mvcrest.services.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestController {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Smith";
    public static final long ID = 1L;
    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Mock
    CustomerService customerService;

    CustomerDTO customerDTO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setCustomerUrl("/api/v1/customers/" + ID);
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<CustomerDTO> listCustomerDto = Arrays.asList(customerDTO, new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(listCustomerDto);

        mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerByLastName() throws Exception {
        when(customerService.getCustomerByLastName(anyString())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/lastName/Smith").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void getCustomerById() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Robert");
        customerDTO.setLastName("Plant");

        CustomerDTO returnedDto = new CustomerDTO();
        returnedDto.setFirstName(customerDTO.getFirstName());
        returnedDto.setLastName(customerDTO.getLastName());
        returnedDto.setCustomerUrl("/api/v1/customer/2");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnedDto);

        mockMvc.perform(post("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Robert")))
                .andExpect(jsonPath("$.lastName", equalTo("Plant")))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customer/2")));
    }

}