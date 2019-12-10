package net.cookingbook.data.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups_of_people")
public class Group extends BaseEntity {

    private String name;
    private String description;
    private List<User> users;
    private List<Post> posts;
    private List<Comment> comments;

    public Group() {
    }


    @Column(name = "group_name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @ManyToMany(targetEntity = Post.class)
    @JoinTable(name = "groups_posts",
            joinColumns = @JoinColumn( name = "group_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id")
    )
    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @ManyToMany(mappedBy = "groupComments")
    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
