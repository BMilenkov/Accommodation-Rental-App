package mk.ukim.finki.emt.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Immutable
@Subselect("SELECT * FROM public.hosts_by_country")
public class HostsPerCountryView {

    @Id
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "num_hosts")
    private Integer numHosts;
}
