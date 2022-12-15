package co.com.poli.showtimeservice.service;

import co.com.poli.showtimeservice.clientFeign.MovieClient;
import co.com.poli.showtimeservice.mapper.ShowtimeInDTOToShowtime;
import co.com.poli.showtimeservice.model.Movie;
import co.com.poli.showtimeservice.persistence.entity.Showtime;
import co.com.poli.showtimeservice.persistence.repository.ShowtimeRepository;
import co.com.poli.showtimeservice.service.dto.ShowtimeInDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService{
    private final ShowtimeRepository repository;
    private final MovieClient client;
    private final ShowtimeInDTOToShowtime showtimeInDTOToShowtime;
    @Override
    public List<Showtime> findAll() {
            return repository.findAll();
    }

    @Override
    public void save(ShowtimeInDTO showtimeInDTO) {
        Showtime showtime = showtimeInDTOToShowtime.map(showtimeInDTO);
        repository.save(showtime);
    }

    @Override
    public Showtime findById(Long id) {
        Showtime showtime = repository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        List<Movie> movies = new ArrayList<>();
        showtime.setMovies(movies);
        if(showtime != null){
            for (Long idMovie: showtime.getMoviesId()) {
                Movie movie = modelMapper.map(client.findByID(idMovie).getData(), Movie.class);
                System.out.println(movie);
                showtime.getMovies().add(modelMapper.map(client.findByID(idMovie).getData(), Movie.class));
            }
        }
        return showtime;
    }

    @Override
    public Showtime update(Long id, Showtime showtime) {
        Showtime showtime2 = findById(id);
        if(showtime2 == null){
            return null;
        }
        showtime2.setDate(showtime.getDate());
        showtime2.setMovies(showtime.getMovies());
        repository.save(showtime2);
        return showtime2;
    }

    @Override
    public List<Showtime> findByMovieId(Long id) {
        return repository.findByMoviesId(id);
    }
}
