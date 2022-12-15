package co.com.poli.movieservice.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false,unique = true)
    private Long id;
    @NotEmpty(message = "El titulo no debe ser vacio")
    @Column
    private String title;
    @NotEmpty(message = "El director no debe ser vacio")
    @Column
    private String director;
    @Min(value = 1, message = "La calificacion no puede ser menor a 1")
    @Max(value = 5, message = "La calificacion no puede ser mayor a 5")
    private int rating;
}
