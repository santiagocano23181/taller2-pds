package co.com.poli.movieservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Booking {
    private Long id;
    private Long userId;
    private Long showtimeId;
    private List<Long> moviesId;
}
