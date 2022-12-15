package co.com.poli.bookingservice.service;

import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.service.dto.BookingInDTO;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    void save(BookingInDTO bookingInDTO);
    Booking findById(Long id);
    void delete(Long id);
    List<Booking> findByUserId(Long id);
    List<Booking> findByShowtimeId(Long id);
    List<Booking> findByMovieId(Long id);
}
