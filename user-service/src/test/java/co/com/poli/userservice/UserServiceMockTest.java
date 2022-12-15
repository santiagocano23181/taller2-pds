package co.com.poli.userservice;

import co.com.poli.userservice.clientFeign.BookingClient;
import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import co.com.poli.userservice.service.UserService;
import co.com.poli.userservice.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;
    private BookingClient bookingClient;
    private UserService userService;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, bookingClient);

        User user = User.builder()
                .id(1L)
                .name("PlaceholderName")
                .lastName("PlaceholderLastname")
                .build();
        User user2 = User.builder()
                .id(2L)
                .name("PlaceholderName2")
                .lastName("PlaceholderLastname2")
                .build();
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2));
    }

    @Test
    public void when_findAll_return_ListUsers(){
        List<User> movies = userService.findAll();

        Assertions.assertThat(movies.size()).isEqualTo(2);
    }

}
