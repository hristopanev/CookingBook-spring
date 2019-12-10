package net.cookingbook.validation.post;

import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.validation.ValidationConstants;
import net.cookingbook.validation.annotation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class PostEditValidator implements org.springframework.validation.Validator {

    private final PostRepository postRepository;

    @Autowired
    public PostEditValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PostServiceModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PostServiceModel postServiceModel = (PostServiceModel) o;

        if (postServiceModel.getName().length() < 3) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.POST_NAME_LENGTH,
                    ValidationConstants.POST_NAME_LENGTH
            );
        }
    }
}
