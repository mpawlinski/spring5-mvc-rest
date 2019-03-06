package pawlinski.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawlinski.spring5mvcrest.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Vendor findVendorByName(String vendorName);
}
