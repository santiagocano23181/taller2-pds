package co.com.poli.bookingservice.clientFeign;

import co.com.poli.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallback = UserClientImplHystrixFallBack.class)
public interface UserClient {

    @GetMapping("/cinema/api/v1/users/{id}")
    Response findByID(@PathVariable("id") Long id);
}
