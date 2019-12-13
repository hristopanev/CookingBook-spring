package net.cookingbook.web.view.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MessageSendBindingModel {

    private String description;

    public MessageSendBindingModel() {
    }


    @NotEmpty(message = "Message cannot be empty")
    @Size(min  =1, message = "Message should be min 1 char")
    @Pattern(regexp = "")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
