package co.com.poli.showtimeservice.service;

import co.com.poli.showtimeservice.persistence.entity.Showtime;
import co.com.poli.showtimeservice.service.dto.ShowtimeInDTO;

import java.util.List;

public interface ShowtimeService {
    List<Showtime> findAll();
    void save(ShowtimeInDTO showtimeInDTO);
    Showtime findById(Long id);
    Showtime update(Long id, Showtime showtime);
    List<Showtime> findByMovieId(Long id);
}
