package pawlinski.spring5mvcrest.services;


import org.springframework.stereotype.Service;
import pawlinski.spring5mvcrest.api.v1.mapper.CustomerMapper;
import pawlinski.spring5mvcrest.api.v1.model.CustomerDTO;
import pawlinski.spring5mvcrest.domain.Customer;
import pawlinski.spring5mvcrest.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByLastName(String lastName) {

        return customerMapper.customerToCustomerDto(customerRepository.findByLastName(lastName));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDto)
                .orElseThrow(RuntimeException::new); //todo implement better error handling
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer savedCustomer = customerRepository.save(customerMapper.customerDtoToCustomer(customerDTO));

        CustomerDTO returnCustomerDto = customerMapper.customerToCustomerDto(savedCustomer);
        returnCustomerDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return returnCustomerDto;
    }
}
