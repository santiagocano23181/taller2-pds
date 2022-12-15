package co.com.poli.userservice.clientFeign;

import co.com.poli.userservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service", fallback = BookingClientImplHystrixFallBack.class)
public interface BookingClient {
    @GetMapping("/cinema/api/v1/bookings/user/{id}")
    Response findByID(@PathVariable("id") Long id);
}
