package co.com.poli.userservice.controller;

import co.com.poli.userservice.helpers.Response;
import co.com.poli.userservice.helpers.ResponseBuild;
import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage(result));
        }

        service.save(user);
        return builder.success(user);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        User user = service.findByID(id);
        if(user==null){
            return builder.failed("Not found");
        }
        service.delete(user);
        return builder.success(user);
    }

    @GetMapping
    public Response findAll(){
        return builder.success(service.findAll());
    }

    @GetMapping("/{id}")
    public Response finById(@PathVariable("id") Long id){
        return builder.success(service.findByID(id));
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
