package net.cookingbook.service.models.services;

import java.util.List;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private List<UserServiceModel> friends;

    private Set<RoleServiceModel> authorities;

    public UserServiceModel() {
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<UserServiceModel> getFriends() {
        return this.friends;
    }

    public void setFriends(List<UserServiceModel> friends) {
        this.friends = friends;
    }

    public Set<RoleServiceModel> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities;
    }
}
