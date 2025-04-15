package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAllByNameContainingIgnoreCaseAndCategoryAndHostProfile_Id(
            String name, Category category, Long id);

    List<Accommodation> findAllByNameContainingIgnoreCaseAndCategory(
            String name, Category category);

    List<Accommodation> findAllByNameContainingIgnoreCaseAndHostProfile_Id(
            String name, Long id);

    List<Accommodation> findAllByCategoryAndHostProfile_Id(Category category, Long id);

    List<Accommodation> findAllByNameContainingIgnoreCase(String name);

    List<Accommodation> findAllByCategory(Category category);

    List<Accommodation> findAllByHostProfile_Id(Long id);
}
