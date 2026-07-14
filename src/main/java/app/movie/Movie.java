package app.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "movies",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"title", "director", "releaseYear"}
                )
        }
)
public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(nullable = false)
    @NotBlank
    private String director;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @NotNull
    @Min(1888)
    @Max(2100)
    @Column(nullable = false)
    private Integer releaseYear;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false, length = 3000)
    @NotBlank
    private String description;

    @Column
    private String posterUrl;

    @NotNull
    @Column(nullable = false)
    private Double averageRating;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}