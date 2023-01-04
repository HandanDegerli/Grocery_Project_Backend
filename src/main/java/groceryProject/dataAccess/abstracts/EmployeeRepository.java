package groceryProject.dataAccess.abstracts;

import groceryProject.entity.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByNationalIdentity(String nationalId);
}
