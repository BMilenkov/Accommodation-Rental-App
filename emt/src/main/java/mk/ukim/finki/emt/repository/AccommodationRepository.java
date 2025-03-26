package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAllByNameContainingIgnoreCaseAndCategoryAndHost_Username(
            String name, Category category, String host_username);

    List<Accommodation> findAllByNameContainingIgnoreCaseAndCategory(
            String name, Category category);

    List<Accommodation> findAllByNameContainingIgnoreCaseAndHost_Username(
            String name, String host_username);

    List<Accommodation> findAllByCategoryAndHost_Username(
            Category category, String host_username);

    List<Accommodation> findAllByNameContainingIgnoreCase(String name);

    List<Accommodation> findAllByCategory(Category category);

    List<Accommodation> findAllByHost_Username(String host_username);
}
