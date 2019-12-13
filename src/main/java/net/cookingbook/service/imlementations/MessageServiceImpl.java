package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.Message;
import net.cookingbook.data.repository.MessageRepository;
import net.cookingbook.service.MessageService;
import net.cookingbook.service.models.services.MessageServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageServiceModel sendMessage(MessageServiceModel messageServiceModel) {
        Message message = this.modelMapper.map(messageServiceModel, Message.class);

        return this.modelMapper.map(this.messageRepository.saveAndFlush(message), MessageServiceModel.class);
    }

    @Override
    public void deleteMessage(String id) {
        Message message = this.messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        this.messageRepository.delete(message);
    }


    @Override
    public MessageServiceModel findMessageById(String id) {
        Message message = this.messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        return this.modelMapper.map(message, MessageServiceModel.class);
    }

    @Override
    public List<MessageServiceModel> findAllByUser(String id) {
        return this.messageRepository.findAllByUser_IdContains(id)
                .stream()
                .map(m -> this.modelMapper.map(m, MessageServiceModel.class))
                .collect(Collectors.toList());
    }
}
