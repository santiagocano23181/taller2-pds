package co.com.poli.showtimeservice.persistence.entity;

import co.com.poli.showtimeservice.model.Movie;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="movie")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false,unique = true)
    private Long id;
    @NotNull(message = "La fecha no debe ser vacia")
    @Column
    private Date date;
    @ElementCollection
    private List<Long> moviesId;
    @Transient
    private List<Movie> movies;
}
