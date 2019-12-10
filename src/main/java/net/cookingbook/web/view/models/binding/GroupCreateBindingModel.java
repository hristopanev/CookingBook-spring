package net.cookingbook.web.view.models.binding;

import net.cookingbook.service.models.services.UserServiceModel;

public class GroupCreateBindingModel {

    private String name;
    private String description;
    private UserServiceModel  users;

    public GroupCreateBindingModel() {
    }

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
