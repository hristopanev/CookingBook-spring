package net.cookingbook.validation.note;

import net.cookingbook.data.repository.NoteRepository;
import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.validation.ValidationConstants;
import net.cookingbook.validation.annotation.Validator;
import net.cookingbook.web.view.models.binding.NoteCreateBindingModel;
import net.cookingbook.web.view.models.binding.PostCreateBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class NoteCreateValidator implements org.springframework.validation.Validator {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteCreateValidator(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NoteCreateBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NoteCreateBindingModel noteCreateBindingModel = (NoteCreateBindingModel) o;

        if (noteCreateBindingModel.getTitle().length() < 3) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH
            );
        }

    }
}
