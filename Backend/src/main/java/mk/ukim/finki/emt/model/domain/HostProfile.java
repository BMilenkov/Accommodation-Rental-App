package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class HostProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Country country;

    @OneToOne
    private User user;

    public HostProfile(Country country, User user) {
        this.country = country;
        this.user = user;
    }
}


