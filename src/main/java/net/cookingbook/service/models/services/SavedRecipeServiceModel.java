package net.cookingbook.service.models.services;

import java.util.List;

public class SavedRecipeServiceModel {

    private String id;
    private UserServiceModel user;
    private PostServiceModel post;

    public SavedRecipeServiceModel() {
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserServiceModel getUser() {
        return this.user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public PostServiceModel getPost() {
        return this.post;
    }

    public void setPost(PostServiceModel post) {
        this.post = post;
    }
}
