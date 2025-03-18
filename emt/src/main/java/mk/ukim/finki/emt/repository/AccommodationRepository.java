package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.Accommodation;
import mk.ukim.finki.emt.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAllByNameContainingIgnoreCaseAndCategoryAndHost_Id(
            String name, Category category, Long hostId);

    List<Accommodation> findAllByNameContainingIgnoreCaseAndCategory(
            String name, Category category);

    List<Accommodation> findAllByNameContainingIgnoreCaseAndHost_Id(
            String name, Long hostId);

    List<Accommodation> findAllByCategoryAndHost_Id(
            Category category, Long hostId);

    List<Accommodation> findAllByNameContainingIgnoreCase(String name);

    List<Accommodation> findAllByCategory(Category category);

    List<Accommodation> findAllByHost_Id(Long hostId);
}
