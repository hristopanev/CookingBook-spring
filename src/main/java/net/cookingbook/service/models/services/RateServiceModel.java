package net.cookingbook.service.models.services;


import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;

import java.util.List;

public class RateServiceModel {

    private String id;
    private int count;
    private PostServiceModel post;
    private List<UserServiceModel> user;

    public RateServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PostServiceModel getPost() {
        return this.post;
    }

    public void setPost(PostServiceModel post) {
        this.post = post;
    }

    public List<UserServiceModel> getUser() {
        return this.user;
    }

    public void setUser(List<UserServiceModel> user) {
        this.user = user;
    }
}
