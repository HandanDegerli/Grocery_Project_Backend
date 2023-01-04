package groceryProject.dataAccess.abstracts;

import groceryProject.entity.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsById(int id);

    boolean existsByName(String name);
}
