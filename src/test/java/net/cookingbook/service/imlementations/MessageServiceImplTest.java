package net.cookingbook.service.imlementations;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.repository.MessageRepository;
import net.cookingbook.service.MessageService;
import net.cookingbook.service.models.services.MessageServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceImplTest extends TestBase {

    @MockBean
    MessageRepository messageRepository;

    @MockBean
    MessageService messageService;

    @Autowired
    MessageService service;

    @Test
    void getMessageForUser_whenNoMessage_shouldReturnEmptyList() {
        List<MessageServiceModel> postServiceModels = service.findAllByUser("1");
        assertEquals(0, postServiceModels.size());
    }

    @Test
    void getMessageForUser_whenMessage_shouldReturnUploader() {
        UserServiceModel user = new UserServiceModel();
        user.setId("1");

        UserServiceModel sender = new UserServiceModel();
        sender.setId("2");

        MessageServiceModel message = new MessageServiceModel();
        message.setSender(sender);
        service.sendMessage(message);

        assertEquals(sender.getId(), message.getSender().getId());

    }
}