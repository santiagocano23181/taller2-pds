package co.com.poli.showtimeservice.persistence.repository;

import co.com.poli.showtimeservice.persistence.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMoviesId(Long id);
}
