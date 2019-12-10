package net.cookingbook.data.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
//TODO MUST MAKE LIST<USER> AND LIST<POST> !!!
    private String description;
    private Date commentDate;
    private List<User> userComment;
    private List<Post> postComment;
    private List<Group> groupComments;


    public Comment() {
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "comment_date")
    public Date getCommentDate() {
        return this.commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "comments_users",
            joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    public List<User> getUserComment() {
        return this.userComment;
    }

    public void setUserComment(List<User> userComment) {
        this.userComment = userComment;
    }

    @ManyToMany(targetEntity = Post.class)
    @JoinTable(name = "comments_posts",
            joinColumns = @JoinColumn( name = "comment_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id")
    )
    public List<Post> getPostComment() {
        return this.postComment;
    }

    public void setPostComment(List<Post> postComment) {
        this.postComment = postComment;
    }

    @ManyToMany(targetEntity = Group.class)
    @JoinTable(name = "comments_groups",
            joinColumns = @JoinColumn( name = "group_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id")
    )
    public List<Group> getGroupComments() {
        return this.groupComments;
    }

    public void setGroupComments(List<Group> groupComments) {
        this.groupComments = groupComments;
    }
}
