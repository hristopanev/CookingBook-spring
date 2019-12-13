package net.cookingbook.service.models.services;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MessageServiceModel {

    private String id;
    private String description;

    private UserServiceModel sender;
    private UserServiceModel userServiceModel;

    public MessageServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public UserServiceModel getSender() {
        return this.sender;
    }

    public void setSender(UserServiceModel sender) {
        this.sender = sender;
    }

    public UserServiceModel getUserServiceModel() {
        return this.userServiceModel;
    }

    public void setUserServiceModel(UserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }
}
