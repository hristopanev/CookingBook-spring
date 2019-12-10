package net.cookingbook.data.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rate")
public class Rate extends BaseEntity {

    private int count;
    private Post post;
    private List<User> user;

    public Rate() {
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @OneToOne(targetEntity = Post.class)
    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    @ManyToMany(targetEntity = User .class)
    @JoinTable(
            name = "users_rates",
            joinColumns = @JoinColumn(name = "rate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    public List<User> getUser() {
        return this.user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
