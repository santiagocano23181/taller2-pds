package co.com.poli.movieservice.clientFeign;

import co.com.poli.movieservice.helpers.Response;
import co.com.poli.movieservice.helpers.ResponseBuild;
import co.com.poli.movieservice.model.Showtime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowtimeClientImplHystrixFallBack implements ShowtimeClient {

    private final ResponseBuild build;

    @Override
    public Response findByID(Long id) {
        return build.success(new Showtime());
    }
}
