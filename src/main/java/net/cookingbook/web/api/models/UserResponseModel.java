package net.cookingbook.web.api.models;

public class UserResponseModel {

    private String id;
    private String username;

    public UserResponseModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
