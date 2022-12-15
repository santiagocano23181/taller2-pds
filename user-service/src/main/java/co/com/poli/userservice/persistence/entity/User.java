package co.com.poli.userservice.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Builder
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true,nullable = false)
    private Long id;
    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name = "name", nullable = false)
    private String name;
    @NotEmpty(message = "El apellido no puede ser vacio")
    @Column(name = "lastName", nullable = false)
    private String lastName;
}
