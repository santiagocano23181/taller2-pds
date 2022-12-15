package co.com.poli.movieservice.service;

import co.com.poli.movieservice.clientFeign.ShowtimeClient;
import co.com.poli.movieservice.model.Booking;
import co.com.poli.movieservice.model.Showtime;
import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository repository;
    private final ShowtimeClient client;

    @Override
    public List<Movie> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Movie movie) {
        repository.save(movie);
    }

    @Override
    public Movie findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Movie movie) {
        ModelMapper modelMapper = new ModelMapper();
        List<Showtime> showtime =
                modelMapper.map(client.findByID(movie.getId()).getData(), new TypeToken<List<Showtime>>() {}.getType());
        if (showtime == null || showtime.isEmpty()) {
            List<Booking> bookings =
                    modelMapper.map(client.findByID(movie.getId()).getData(), new TypeToken<List<Booking>>() {}.getType());
            if (bookings == null || bookings.isEmpty()) {
                repository.delete(movie);
            }
        }

    }
}
