package net.cookingbook.validation.post;

import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.validation.ValidationConstants;
import net.cookingbook.validation.annotation.Validator;
import net.cookingbook.web.view.models.binding.PostCreateBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class PostCreateValidator implements org.springframework.validation.Validator {

    private final PostRepository postRepository;

    @Autowired
    public PostCreateValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PostCreateBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PostCreateBindingModel postCreateBindingModel = (PostCreateBindingModel) o;

        if (postCreateBindingModel.getName().length() < 3) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.POST_NAME_LENGTH,
                    ValidationConstants.POST_NAME_LENGTH
            );
        }

    }
}
