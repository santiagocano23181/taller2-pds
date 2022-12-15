package co.com.poli.bookingservice.controller;

import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.persistence.entity.Booking;
import co.com.poli.bookingservice.service.BookingService;
import co.com.poli.bookingservice.service.dto.BookingInDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody BookingInDTO bookingInDTO, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage(result));
        }

        service.save(bookingInDTO);
        return builder.success(bookingInDTO);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Booking booking = (Booking) findById(id).getData();
        if(booking==null){
            return builder.failed("Not found");
        }
        service.delete(id);
        return builder.success(booking);
    }

    @GetMapping
    public Response findAll(){
        return builder.success(service.findAll());
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        return builder.success(service.findById(id));
    }

    @GetMapping("/user/{userId}")
    public Response findByUserId(@PathVariable("userId") Long id){
        return builder.success(service.findByUserId(id));
    }

    @GetMapping("/showtime/{showtimeId}")
    public Response findByShowTimeId(@PathVariable("showtimeId") Long id){
        return builder.success(service.findByShowtimeId(id));
    }

    @GetMapping("/movie/{movieId}")
    public Response findByMovieId(@PathVariable("movieId") Long id){
        return builder.success(service.findByMovieId(id));
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
