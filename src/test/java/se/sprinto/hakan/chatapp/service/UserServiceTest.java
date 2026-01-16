package se.sprinto.hakan.chatapp.service;

import org.junit.jupiter.api.BeforeEach;
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

    User user;

    @BeforeEach
    void setUp() {
        user = new User("jim", "1234");
    }
    //Testar när User och Password är rätt.
    @Test
    void loginReturnsUserWhenCredentialsAreCorrect() {

        when(userRepository.findByUsernameAndPassword("jim", "1234"))
                .thenReturn(user);

        User result = userService.login("jim", "1234");

        assertNotNull(result);
        assertEquals("jim", result.getUsername());
        verify(userRepository).findByUsernameAndPassword("jim", "1234");
    }
    //Testar när User och Password är fel.
    @Test
    void loginReturnsNullWhenCredentialsAreWrong() {

        when(userRepository.findByUsernameAndPassword("jim", "wrong")).thenReturn(null);

        User result = userService.login("jim", "wrong");

        assertNull(result);
        verify(userRepository).findByUsernameAndPassword("jim","wrong");
    }
    //Testar
    @Test
    void registerCallsRepositorySave() {

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.register(user);

        assertNotNull(result);
        verify(userRepository).save(user);
    }
    //Testar att skapa en ny User.
    @Test
    void registerCreatesNewUser() {
        User user1 = new User("pelle", "key");

        when(userRepository.save(user1)).thenReturn(user1);

        User result = userService.register(user1);

        assertNotNull(result);
        assertEquals("pelle", result.getUsername());
    }
}