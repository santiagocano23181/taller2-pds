package co.com.poli.movieservice;

import co.com.poli.movieservice.clientFeign.ShowtimeClient;
import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import co.com.poli.movieservice.service.MovieService;
import co.com.poli.movieservice.service.MovieServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MovieServiceMockTest {

    @Mock
    private MovieRepository movieRepository;
    private ShowtimeClient showtimeClient;
    private MovieService movieService;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.openMocks(this);
        movieService = new MovieServiceImpl(movieRepository, showtimeClient);

        Movie movie = Movie.builder()
                .id(5L)
                .title("Placeholder")
                .director("Jhon Doe")
                .rating(1)
                .build();
        Movie movie2 = Movie.builder()
                .id(6L)
                .title("Placeholder2")
                .director("Jhon Doe2")
                .rating(3)
                .build();
        Mockito.when(movieRepository.findById(5L)).thenReturn(Optional.of(movie));
        Mockito.when(movieRepository.findAll()).thenReturn(Arrays.asList(movie, movie2));
    }

    @Test
    public void when_findById_return_Movie(){
        Movie movie = movieService.findById(5L);
        Assertions.assertThat(movie.getTitle()).isEqualTo("Placeholder");
    }

    @Test
    public void when_findAll_return_All(){
        List<Movie> movies = movieService.findAll();

        Assertions.assertThat(movies.size()).isEqualTo(2);
    }

}
