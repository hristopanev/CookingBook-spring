package net.cookingbook.validation.user;

import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.validation.ValidationConstants;
import net.cookingbook.validation.annotation.Validator;
import net.cookingbook.web.view.models.binding.UserRegisterBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class UserRegisterValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterBindingModel userRegisterBindingModel = (UserRegisterBindingModel) o;

        if (userRegisterBindingModel.getUsername() == null) {
            errors.rejectValue(
                    "username",
                    ValidationConstants.USERNAME_EMPTY,
                    ValidationConstants.USERNAME_EMPTY
            );
        }

        try {
            if (this.userRepository.findByUsername(userRegisterBindingModel.getUsername()).isPresent()) {
                errors.rejectValue(
                        "username",
                        String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userRegisterBindingModel.getUsername()),
                        String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userRegisterBindingModel.getUsername())
                );
            }

            if (userRegisterBindingModel.getUsername().length() < 3 || userRegisterBindingModel.getUsername().length() > 20) {
                errors.rejectValue(
                        "username",
                        ValidationConstants.USERNAME_LENGTH,
                        ValidationConstants.USERNAME_LENGTH
                );
            }
        }catch (Exception ignored) {

        }

        try {
            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                errors.rejectValue(
                        "password",
                        ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                        ValidationConstants.PASSWORDS_DO_NOT_MATCH
                );
            }
        } catch (Exception ignored) {

        }

        if (userRegisterBindingModel.getPassword() == null || userRegisterBindingModel.getConfirmPassword() == null) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.PASSWORD_CANNOT_BE_EMPTY,
                    ValidationConstants.PASSWORD_CANNOT_BE_EMPTY
            );
        }

        try {
            if (userRegisterBindingModel.getPassword().length() < 5) {
                errors.rejectValue(
                        "password",
                        ValidationConstants.PASSWORD_LENGTH,
                        ValidationConstants.PASSWORD_LENGTH
                );
            }
        } catch (Exception ignored) {

        }

        if (userRegisterBindingModel.getEmail() == null) {
            errors.rejectValue(
                    "email",
                    ValidationConstants.EMAIL_CANNOT_BE_EMPTY,
                    ValidationConstants.EMAIL_CANNOT_BE_EMPTY
            );
        }

        if (this.userRepository.findByEmail(userRegisterBindingModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail())
            );
        }
    }
}
