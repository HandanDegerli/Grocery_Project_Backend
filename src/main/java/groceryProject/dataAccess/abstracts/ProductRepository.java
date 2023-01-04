package groceryProject.dataAccess.abstracts;

import groceryProject.entity.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsById(int id);
    boolean existsByName(String name);
}
