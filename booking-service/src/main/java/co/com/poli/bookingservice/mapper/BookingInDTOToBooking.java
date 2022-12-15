package co.com.poli.bookingservice.mapper;

import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.service.dto.BookingInDTO;
import org.springframework.stereotype.Component;

@Component
public class BookingInDTOToBooking implements IMapper<BookingInDTO, Booking> {
    @Override
    public Booking map(BookingInDTO in) {
        Booking booking = new Booking();
        booking.setId(in.getId());
        booking.setUserId(in.getUserId());
        booking.setShowtimeId(in.getShowtimeId());
        booking.setMoviesId(in.getMoviesId());
        return booking;
    }
}
