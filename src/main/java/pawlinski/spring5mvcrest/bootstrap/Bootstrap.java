package pawlinski.spring5mvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pawlinski.spring5mvcrest.domain.Category;
import pawlinski.spring5mvcrest.domain.Customer;
import pawlinski.spring5mvcrest.repositories.CategoryRepository;
import pawlinski.spring5mvcrest.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner { 

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories loaded: " + categoryRepository.count());
    }

    private void loadCustomers() {

        Customer anthony = new Customer();
        anthony.setFirstName("Anthony");
        anthony.setLastName("Kiedis");

        Customer chad = new Customer();
        chad.setFirstName("Chad");
        chad.setLastName("Smith");

        Customer john = new Customer();
        john.setFirstName("John");
        john.setLastName("Frusciante");

        Customer michael = new Customer();
        michael.setFirstName("Michael");
        michael.setLastName("Balzary");

        customerRepository.save(anthony);
        customerRepository.save(chad);
        customerRepository.save(john);
        customerRepository.save(michael);

        System.out.println("Customers loaded: " + customerRepository.count());
    }
}
