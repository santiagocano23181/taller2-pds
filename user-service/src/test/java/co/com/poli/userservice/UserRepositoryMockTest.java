package co.com.poli.userservice;

import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class UserRepositoryMockTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void when_findAll_return_ListUsers(){
        User user = User.builder()
                .name("Santiago")
                .lastName("Cano")
                .build();
        User user1 = userRepository.save(user);
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void when_save_return_Movie(){
        User user = User.builder()
                .name("Santiago")
                .lastName("Cano")
                .build();
        User user1 = userRepository.save(user);
        Assertions.assertThat(user1.getName()).isEqualTo("Santiago");
    }
}
