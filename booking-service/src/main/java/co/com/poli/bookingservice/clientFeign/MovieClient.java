package co.com.poli.bookingservice.clientFeign;

import co.com.poli.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service", fallback = MovieClientImplHystrixFallBack.class)
public interface MovieClient {

    @GetMapping("/cinema/api/v1/movies/{id}")
    Response findByID(@PathVariable("id") Long id);
}
