package co.com.poli.userservice.service;

import co.com.poli.userservice.clientFeign.BookingClient;
import co.com.poli.userservice.model.Booking;
import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final BookingClient client;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void delete(User user) {
        ModelMapper modelMapper = new ModelMapper();
        List<Booking> bookings =
                modelMapper.map(client.findByID(user.getId()).getData(), new TypeToken<List<Booking>>() {}.getType());
        if(bookings == null || bookings.isEmpty()){
            repository.delete(user);
        }
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByID(Long id) {
        return repository.findById(id).orElse(null);
    }
}
