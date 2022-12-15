package co.com.poli.movieservice.clientFeign;

import co.com.poli.movieservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service", fallback = BookingClientImplHystrixFallBack.class)
public interface BookingClient {
    @GetMapping("/cinema/api/v1/bookings/movie/{id}")
    Response findByID(@PathVariable("id") Long id);
}
