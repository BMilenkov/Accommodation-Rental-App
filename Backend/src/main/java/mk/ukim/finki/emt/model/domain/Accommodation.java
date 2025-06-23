package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.listeners.AccommodationEventListener;
import mk.ukim.finki.emt.model.enumerations.Category;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AccommodationEventListener.class)
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private HostProfile hostProfile;

    private Integer numRooms;

    private Boolean isRented;

    @ManyToMany(mappedBy = "accommodations")
    private List<ReservationCart> reservationCarts;

    public Accommodation(String name, Category category, HostProfile hostProfile, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.hostProfile = hostProfile;
        this.numRooms = numRooms;
        this.isRented = false;
    }
}
