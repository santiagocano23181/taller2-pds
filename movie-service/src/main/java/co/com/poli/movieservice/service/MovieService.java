package co.com.poli.movieservice.service;

import co.com.poli.movieservice.persistence.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    void save(Movie movie);
    Movie findById(Long id);
    void delete(Movie movie);
}
