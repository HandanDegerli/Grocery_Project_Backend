package groceryProject.dataAccess.abstracts;

import groceryProject.entity.concretes.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Integer> {
    boolean existsByTaxNumber(String taxNumber);
}
