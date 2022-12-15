package co.com.poli.movieservice;

import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class MovieRepositoryMockTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void when_findAll_return_ListMovies(){
        Movie movie = Movie.builder()
                .title("Avatar")
                .director("James Cameron")
                .rating(4)
                .build();
        Movie movie2 = movieRepository.save(movie);
        System.out.println(movie2);
        List<Movie> movies = movieRepository.findAll();
        Assertions.assertThat(movies.size()).isEqualTo(1);
    }

    @Test
    public void when_save_return_Movie(){
        Movie movie = Movie.builder()
                .title("Avatar")
                .director("James Cameron")
                .rating(4)
                .build();
        Movie movie2 = movieRepository.save(movie);
        Assertions.assertThat(movie2.getTitle()).isEqualTo("Avatar");
    }
}
