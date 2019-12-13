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

        if (postCreateBindingModel.getImage().isEmpty()) {
            errors.rejectValue(
                    "image",
                    ValidationConstants.POST_IMAGE,
                    ValidationConstants.POST_IMAGE
            );
        }

        if (postCreateBindingModel.getProducts().length() < 5) {
            errors.rejectValue(
                    "products",
                    ValidationConstants.POST_PRODUCTS_LENGTH,
                    ValidationConstants.POST_PRODUCTS_LENGTH
            );
        }

        if (postCreateBindingModel.getDescription().length() < 15) {
            errors.rejectValue(
                    "description",
                    ValidationConstants.POST_DESCRIPTION_LENGTH,
                    ValidationConstants.POST_DESCRIPTION_LENGTH
            );
        }
    }


}
