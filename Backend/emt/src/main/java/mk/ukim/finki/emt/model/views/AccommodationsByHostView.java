package mk.ukim.finki.emt.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Subselect("SELECT * FROM public.accommodations_by_host")
@Immutable
public class AccommodationsByHostView {

    @Id
    @Column(name = "host_profile_id")
    private Long hostProfileId;

    @Column(name = "num_accommodations")
    private Integer numAccommodations;
}
