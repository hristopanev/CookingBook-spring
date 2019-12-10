package net.cookingbook.validation.user;

import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.validation.ValidationConstants;
import net.cookingbook.validation.annotation.Validator;
import net.cookingbook.web.view.models.binding.UserRegisterBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class UserLoginValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserLoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserServiceModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserServiceModel userServiceModel = (UserServiceModel) o;

        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);

        if (userServiceModel.getPassword() != null && !userServiceModel.getPassword().equals(user.getPassword())) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.WRONG_PASSWORD,
                    ValidationConstants.WRONG_PASSWORD
            );
        }

        if (userServiceModel.getUsername() != null && !userServiceModel.getPassword().equals(user.getUsername())) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.WRONG_USERNAME,
                    ValidationConstants.WRONG_USERNAME
            );
        }
    }
}
