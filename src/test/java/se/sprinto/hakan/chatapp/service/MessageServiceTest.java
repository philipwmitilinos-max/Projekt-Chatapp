package se.sprinto.hakan.chatapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.repository.MessageRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    MessageService messageService;

    @Test
    void saveMessage_callsRepositorySave() {
        Message message = mock(Message.class);

        messageService.save(message);

        verify(messageRepository).save(message);
    }
}