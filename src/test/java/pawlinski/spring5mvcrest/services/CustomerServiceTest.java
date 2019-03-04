package pawlinski.spring5mvcrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pawlinski.spring5mvcrest.api.v1.mapper.CustomerMapper;
import pawlinski.spring5mvcrest.api.v1.model.CustomerDTO;
import pawlinski.spring5mvcrest.domain.Customer;
import pawlinski.spring5mvcrest.repositories.CustomerRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    public static final String FIRST_NAME = "John";
    public static final Long ID = 1L;
    public static final String LAST_NAME = "Smith";

    @Mock
    CustomerRepository customerRepository;

    CustomerServiceImpl customerService;

    Customer customer1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);

        customer1 = new Customer();
        customer1.setId(ID);
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);
    }

    @Test
    public void testGetAllCustomers() {
        //given

        List<Customer> list = Arrays.asList(customer1, new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(list);

        //when
        List<CustomerDTO> listCustomerDto = customerService.getAllCustomers();

        //then
        assertNotNull(listCustomerDto);
        assertEquals(3, listCustomerDto.size());
    }

    @Test
    public void testGetCustomerByLastName() {
        //given
        when(customerRepository.findByLastName(anyString())).thenReturn(customer1);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByLastName(LAST_NAME);

        //then
        assertNotNull(customerDTO);
        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }

    @Test
    public void testCreateNewCustomer() {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Robert");
        customerDTO.setLastName("Plant");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(2L);
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedCustomerDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(savedCustomerDto.getFirstName(), savedCustomer.getFirstName());
        assertEquals(savedCustomerDto.getLastName(), savedCustomer.getLastName());
        assertEquals("/api/v1/customer/2", savedCustomerDto.getCustomerUrl());
    }
}