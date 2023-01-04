package groceryProject.dataAccess.abstracts;

import groceryProject.entity.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {
    boolean existsByNationalIdentity(String nationalId);
}
