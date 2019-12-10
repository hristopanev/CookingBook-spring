package net.cookingbook.data.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "saved_recipes")
public class SavedRecipe extends BaseEntity {

    private User user;
    private Post post;

    public SavedRecipe() {
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @ManyToMany(targetEntity = Post.class)
//    @JoinTable(
//            name = "saved_user_recipes",
//            joinColumns = @JoinColumn(name = "saved_recipes_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id")
//    )
    @OneToOne(targetEntity = Post.class)
    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
