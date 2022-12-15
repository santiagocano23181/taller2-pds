package co.com.poli.movieservice.controller;

import co.com.poli.movieservice.helpers.ResponseBuild;
import co.com.poli.movieservice.helpers.Response;
import co.com.poli.movieservice.persistence.entity.Movie;
import co.com.poli.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService service;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody Movie movie, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage(result));
        }

        service.save(movie);
        return builder.success(movie);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Movie movie = (Movie) findById(id).getData();
        if(movie==null){
            return builder.failed("Not found");
        }
        service.delete(movie);
        return builder.success(movie);
    }

    @GetMapping
    public Response findAll(){
        return builder.success(service.findAll());
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        return builder.success(service.findById(id));
    }


    private List<Map<String, String>> formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put(error.getField(), error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        return errors;
    }
}
