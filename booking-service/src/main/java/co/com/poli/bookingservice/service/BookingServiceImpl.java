package co.com.poli.bookingservice.service;

import co.com.poli.bookingservice.clientFeign.ShowtimeClient;
import co.com.poli.bookingservice.clientFeign.UserClient;
import co.com.poli.bookingservice.mapper.BookingInDTOToBooking;
import co.com.poli.bookingservice.model.Showtime;
import co.com.poli.bookingservice.model.User;
import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.persistence.repository.BookingRepository;
import co.com.poli.bookingservice.service.dto.BookingInDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository repository;
    private final ShowtimeClient showtimeClient;
    private final UserClient userClient;
    private final BookingInDTOToBooking bookingInDTOToBooking;

    @Override
    public List<Booking> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(BookingInDTO bookingInDTO) {
        Booking booking = bookingInDTOToBooking.map(bookingInDTO);
        repository.save(booking);
    }

    @Override
    public Booking findById(Long id) {
        Booking booking = repository.findById(id).orElse(null);
        if (booking == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        Showtime showtime =
                modelMapper.map(showtimeClient.findByID(booking.getShowtimeId()).getData(), Showtime.class);

        booking.setShowtime(showtime);

        User user =
                modelMapper.map(userClient.findByID(booking.getUserId()).getData(), User.class);

        booking.setUser(user);

        return booking;

    }

    @Override
    public void delete(Long id) {
        Booking booking = findById(id);
        repository.delete(booking);
    }

    @Override
    public List<Booking> findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public List<Booking> findByShowtimeId(Long id) {
        return repository.findByShowtimeId(id);
    }

    @Override
    public List<Booking> findByMovieId(Long id) {
        return repository.findByMoviesId(id);
    }
}
