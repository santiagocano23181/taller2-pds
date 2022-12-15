package co.com.poli.showtimeservice.controller;

import co.com.poli.showtimeservice.helpers.Response;
import co.com.poli.showtimeservice.helpers.ResponseBuild;
import co.com.poli.showtimeservice.persistence.entity.Showtime;
import co.com.poli.showtimeservice.service.ShowtimeService;
import co.com.poli.showtimeservice.service.dto.ShowtimeInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService service;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody ShowtimeInDTO showtime, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(formatMessage(result));
        }
        service.save(showtime);
        return builder.success(showtime);
    }

    @GetMapping
    public Response findAll(){
        List<Showtime> showtime = service.findAll();
        return builder.success(showtime);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable("id") Long id) {
        Showtime showtime = service.findById(id);
        if (showtime == null) {
            return builder.success();
        }
        return builder.success(showtime);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody Showtime showtime, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(formatMessage(result));
        }
        service.update(id, showtime);
        return builder.success(showtime);
    }

    @GetMapping("/movie/{id}")
    public Response getByMovieId(@PathVariable("id") Long id) {
        List<Showtime> showtimes = service.findByMovieId(id);
        if (showtimes == null || showtimes.isEmpty()) {
            return builder.success();
        }
        return builder.success(showtimes);
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
