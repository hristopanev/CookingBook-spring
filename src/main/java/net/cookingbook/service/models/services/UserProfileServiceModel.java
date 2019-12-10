package net.cookingbook.service.models.services;

import net.cookingbook.data.models.User;

public class UserProfileServiceModel extends BaseServiceModel {

    private String firstName;
    private String lastName;
    private String imageUrl;
    private User user;

    public UserProfileServiceModel() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
