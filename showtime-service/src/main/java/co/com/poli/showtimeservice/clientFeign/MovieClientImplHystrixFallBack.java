package co.com.poli.showtimeservice.clientFeign;

import co.com.poli.showtimeservice.helpers.Response;
import co.com.poli.showtimeservice.helpers.ResponseBuild;
import co.com.poli.showtimeservice.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieClientImplHystrixFallBack implements MovieClient {

    private final ResponseBuild build;

    @Override
    public Response findByID(Long id) {
        return build.success(new Movie());
    }
}
