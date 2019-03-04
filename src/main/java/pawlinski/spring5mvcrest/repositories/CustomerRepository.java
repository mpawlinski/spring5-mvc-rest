package pawlinski.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawlinski.spring5mvcrest.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByLastName(String lastName);
}
