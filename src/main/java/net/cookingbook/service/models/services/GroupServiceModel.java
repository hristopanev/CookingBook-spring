package net.cookingbook.service.models.services;

import java.util.List;

public class GroupServiceModel {

    private String id;
    private String name;
    private String description;
    private List<UserServiceModel> users;
    private List<PostServiceModel> posts;

    public GroupServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<UserServiceModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserServiceModel> users) {
        this.users = users;
    }

    public List<PostServiceModel> getPosts() {
        return this.posts;
    }

    public void setPosts(List<PostServiceModel> posts) {
        this.posts = posts;
    }
}
