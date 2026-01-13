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
    void login_returnsUser_whenCredentialsAreCorrect() {
        User user = new User("chatuser", "password");

        when(userRepository.findByUsernameAndPassword("chatuser", "password"))
                .thenReturn(user);

        User result = userService.login("chatuser", "password");

        assertNotNull(result);
        assertEquals("chatuser", result.getUsername());
        verify(userRepository).findByUsernameAndPassword("chatuser", "password");
    }

    @Test
    void register_callsRepositorySave() {
        User user = new User("newuser", "secret");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.register(user);

        assertNotNull(result);
        verify(userRepository).save(user);
    }
}