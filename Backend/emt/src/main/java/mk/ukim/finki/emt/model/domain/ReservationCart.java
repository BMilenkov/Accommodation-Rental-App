package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.model.enumerations.ReservationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class ReservationCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Accommodation> accommodations;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public ReservationCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.accommodations = new ArrayList<Accommodation>();
        this.status = ReservationStatus.CREATED;
    }
}
