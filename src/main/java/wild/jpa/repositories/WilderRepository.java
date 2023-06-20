package wild.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wild.jpa.models.Wilder;

import java.util.List;

@Repository
public interface WilderRepository extends JpaRepository<Wilder, Long> {
    List<Wilder> findWilderByCategory(String category);
    List<Wilder> findWilderByEmail (String email);
}
