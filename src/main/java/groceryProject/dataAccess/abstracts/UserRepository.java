package groceryProject.dataAccess.abstracts;

import groceryProject.entity.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsById(int id);
    boolean existsByEmail(String email);
}
