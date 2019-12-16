package net.cookingbook.validation.group;

import net.cookingbook.data.repository.GroupRepository;
import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.validation.ValidationConstants;
import net.cookingbook.validation.annotation.Validator;
import net.cookingbook.web.view.models.binding.GroupCreateBindingModel;
import net.cookingbook.web.view.models.binding.PostCreateBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class GroupCreateValidator implements org.springframework.validation.Validator {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupCreateValidator(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GroupCreateBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        GroupCreateBindingModel groupCreateBindingModel = (GroupCreateBindingModel) o;

        if (groupCreateBindingModel.getName().length() < 3) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.GROUP_NAME_LENGTH,
                    ValidationConstants.GROUP_NAME_LENGTH
            );
        }

    }
}
