package mk.ukim.finki.emt.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Integer rating;

    @ManyToOne
    private Accommodation accommodation;

    public Review(String comment, Integer rating, Accommodation accommodation) {
        this.comment = comment;
        this.rating = rating;
        this.accommodation = accommodation;
    }
}
