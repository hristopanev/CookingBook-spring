package net.cookingbook.web.view.models.binding;

import net.cookingbook.service.models.services.GroupServiceModel;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;

import java.util.Date;

public class CommentCreateBindingModel {

    private String description;
    private Date commentDate;
    private UserServiceModel userComment;
    private PostServiceModel postComment;
    private GroupServiceModel groupComments;


    public CommentCreateBindingModel() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCommentDate() {
        return this.commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public UserServiceModel getUserComment() {
        return this.userComment;
    }

    public void setUserComment(UserServiceModel userComment) {
        this.userComment = userComment;
    }

    public PostServiceModel getPostComment() {
        return this.postComment;
    }

    public void setPostComment(PostServiceModel postComment) {
        this.postComment = postComment;
    }

    public GroupServiceModel getGroupComments() {
        return this.groupComments;
    }

    public void setGroupComments(GroupServiceModel groupComments) {
        this.groupComments = groupComments;
    }
}
