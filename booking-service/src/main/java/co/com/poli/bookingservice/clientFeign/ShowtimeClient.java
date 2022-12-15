package co.com.poli.bookingservice.clientFeign;

import co.com.poli.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "showtime-service", fallback = ShowtimeClientImplHystrixFallBack.class)
public interface ShowtimeClient {

    @GetMapping("/cinema/api/v1/showtimes/{id}")
    Response findByID(@PathVariable("id") Long id);
}
