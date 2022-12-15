package co.com.poli.bookingservice.clientFeign;

import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClientImplHystrixFallBack implements UserClient {

    private final ResponseBuild build;

    @Override
    public Response findByID(Long id) {
        return build.success(new User());
    }
}
