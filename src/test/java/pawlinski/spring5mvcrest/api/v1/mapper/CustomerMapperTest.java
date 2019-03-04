package pawlinski.spring5mvcrest.api.v1.mapper;

import org.junit.Test;
import pawlinski.spring5mvcrest.api.v1.model.CustomerDTO;
import pawlinski.spring5mvcrest.domain.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerMapperTest {

    public static final String NAME = "John";
    public static final Long ID = 1L;
    public static final String LAST_NAME = "Smith";

    @Test
    public void testCustomerToCustomerDto() {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(NAME);
        customer.setLastName(LAST_NAME);

        //when
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDto(customer);

        //then
        assertNotNull(customerDTO);
        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}