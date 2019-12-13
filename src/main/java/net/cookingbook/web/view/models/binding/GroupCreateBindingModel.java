package net.cookingbook.web.view.models.binding;

import net.cookingbook.service.models.services.UserServiceModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class GroupCreateBindingModel {

    private String name;
    private String description;
    private UserServiceModel  users;

    public GroupCreateBindingModel() {
    }

    @NotEmpty(message = "Group name cannot be empty")
    @Size(min = 2, max = 30, message = "Group should be between 2 and 30")
    @Pattern(regexp = "")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserServiceModel getUsers() {
        return this.users;
    }

    public void setUsers(UserServiceModel users) {
        this.users = users;
    }
}
