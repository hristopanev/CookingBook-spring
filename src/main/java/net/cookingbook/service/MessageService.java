package net.cookingbook.service;

import net.cookingbook.service.models.services.MessageServiceModel;

import java.util.List;

public interface MessageService {

    MessageServiceModel sendMessage(MessageServiceModel messageServiceModel);

    void deleteMessage(String id);

    MessageServiceModel findMessageById(String id);

    List<MessageServiceModel> findAllByUser(String id);
}
