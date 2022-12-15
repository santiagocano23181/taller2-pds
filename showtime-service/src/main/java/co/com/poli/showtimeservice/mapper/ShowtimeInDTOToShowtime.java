package co.com.poli.showtimeservice.mapper;

import co.com.poli.showtimeservice.persistence.entity.Showtime;
import co.com.poli.showtimeservice.service.dto.ShowtimeInDTO;
import org.springframework.stereotype.Component;

@Component
public class ShowtimeInDTOToShowtime implements IMapper<ShowtimeInDTO, Showtime>{
    @Override
    public Showtime map(ShowtimeInDTO in) {
        Showtime showtime = new Showtime();
        showtime.setId(in.getId());
        showtime.setDate(in.getDate());
        showtime.setMoviesId(in.getMoviesId());
        return showtime;
    }
}
