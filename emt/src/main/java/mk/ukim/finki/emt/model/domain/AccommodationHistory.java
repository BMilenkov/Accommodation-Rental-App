package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.model.enumerations.AccommodationChangeType;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class AccommodationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accommodationId;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccommodationChangeType changeType;

    private LocalDateTime timestamp;
}