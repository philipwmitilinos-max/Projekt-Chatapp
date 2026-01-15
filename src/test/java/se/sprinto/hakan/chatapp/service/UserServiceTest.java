package se.sprinto.hakan.chatapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void loginReturnsUserWhenCredentialsAreCorrect() {
        User user = new User("jim", "1234");

        when(userRepository.findByUsernameAndPassword("jim", "1234"))
                .thenReturn(user);

        User result = userService.login("jim", "1234");

        assertNotNull(result);
        assertEquals("jim", result.getUsername());
        verify(userRepository).findByUsernameAndPassword("jim", "1234");
    }

    @Test
    void registerCallsRepositorySave() {
        User user = new User("newuser", "secret");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.register(user);

        assertNotNull(result);
        verify(userRepository).save(user);
    }
}