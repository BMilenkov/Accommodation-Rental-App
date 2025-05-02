package mk.ukim.finki.emt.repository;

import org.springframework.transaction.annotation.Transactional;
import mk.ukim.finki.emt.model.views.AccommodationsByHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationsByHostViewRepository
        extends JpaRepository<AccommodationsByHostView, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodations_by_host", nativeQuery = true)
    void refreshMaterializedView();

}
