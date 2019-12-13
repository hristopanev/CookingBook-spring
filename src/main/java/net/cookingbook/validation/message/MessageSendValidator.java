package net.cookingbook.validation.message;

import net.cookingbook.data.repository.MessageRepository;
import net.cookingbook.service.models.services.MessageServiceModel;
import net.cookingbook.validation.ValidationConstants;
import net.cookingbook.validation.annotation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class MessageSendValidator implements org.springframework.validation.Validator {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageSendValidator(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MessageServiceModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MessageServiceModel messageServiceModel = (MessageServiceModel) o;

        if (messageServiceModel.getDescription().length() < 2) {
            errors.rejectValue(
                    "description",
                    ValidationConstants.MESSAGE_LENGTH,
                    ValidationConstants.MESSAGE_LENGTH
            );
        }

    }
}
