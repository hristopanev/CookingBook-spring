package net.cookingbook.validation.user;


import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.validation.ValidationConstants;
import net.cookingbook.validation.annotation.Validator;
import net.cookingbook.web.view.models.binding.UserEditBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

@Validator
public class UserEditValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserEditValidator(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserServiceModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserServiceModel userServiceModel = (UserServiceModel) o;

        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);


        if (!user.getEmail().equals(userServiceModel.getEmail()) && this.userRepository.findByEmail(userServiceModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userServiceModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userServiceModel.getEmail())
            );
        }
    }
}
