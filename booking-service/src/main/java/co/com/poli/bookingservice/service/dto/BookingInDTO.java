package co.com.poli.bookingservice.service.dto;

import co.com.poli.bookingservice.model.Movie;
import co.com.poli.bookingservice.model.Showtime;
import co.com.poli.bookingservice.model.User;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class BookingInDTO {
    private Long id;
    private Long userId;
    private User user;
    private Long showtimeId;
    private List<Long> moviesId;
}
