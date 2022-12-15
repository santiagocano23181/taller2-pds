package co.com.poli.bookingservice.persistence.entity;


import co.com.poli.bookingservice.model.Movie;
import co.com.poli.bookingservice.model.Showtime;
import co.com.poli.bookingservice.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false,unique = true)
    private Long id;
    @NotNull(message = "El usuario no puede ser vacio")
    private Long userId;
    @Transient
    private User user;
    @NotNull(message = "El tiempo de la funcion no puede ser vacio")
    private Long showtimeId;
    @Transient
    private Showtime showtime;
    @ElementCollection
    private List<Long> moviesId;
    @Transient
    private List<Movie> movies;
}
