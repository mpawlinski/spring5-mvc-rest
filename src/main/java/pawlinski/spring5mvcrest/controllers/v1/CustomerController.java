package pawlinski.spring5mvcrest.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pawlinski.spring5mvcrest.api.v1.model.CustomerDTO;
import pawlinski.spring5mvcrest.api.v1.model.CustomerListDTO;
import pawlinski.spring5mvcrest.services.CustomerService;

@Api(value = "Customer API")
@RestController
@RequestMapping(CustomerController.BASE_CUSTOMER_URL)
public class CustomerController {

    public static final String BASE_CUSTOMER_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "View list of Customers")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers() {
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @ApiOperation(value = "Get Customer by last name")
    @GetMapping("/lastName/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerByLastName(@PathVariable String lastName) {
        return customerService.getCustomerByLastName(lastName);
    }

    @ApiOperation(value = "Get Customer by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @ApiOperation(value = "Create a new Customer.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @ApiOperation(value = "Update an existing Customer.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateExistingCustomer(id, customerDTO);
    }

    @ApiOperation(value = "Update Customer's property")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id, customerDTO);
    }

    @ApiOperation(value = "Delete a Customer by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
