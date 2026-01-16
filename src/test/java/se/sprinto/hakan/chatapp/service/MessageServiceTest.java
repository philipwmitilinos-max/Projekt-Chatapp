package se.sprinto.hakan.chatapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    MessageService messageService;

    User user;
    Message message;
    List<Message> messages;

    @BeforeEach
    void setUp() {
        user =new User("jim", "1234");
        message = new Message(user, "Hello world", LocalDateTime.now());
        messages = List.of(message);
    }

    @Test
    void saveMessageCallsRepositorySave() {

        messageService.save(message);

        verify(messageRepository).save(message);
    }

    @Test
    void getMessageReturnsMessagesForUser() {

        when(messageRepository.findByUserId(1L)).thenReturn(messages);

        List<Message> result = messageService.getMessages(1L);

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals("Hello world", result.get(0).getText());
        verify(messageRepository).findByUserId(1L);
    }
}