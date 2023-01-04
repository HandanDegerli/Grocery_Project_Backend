package groceryProject.dataAccess.abstracts;

import groceryProject.entity.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
