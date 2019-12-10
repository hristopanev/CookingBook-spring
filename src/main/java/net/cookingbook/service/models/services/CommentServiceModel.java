package net.cookingbook.service.models.services;

import net.cookingbook.data.models.Group;
import net.cookingbook.data.models.Post;
import net.cookingbook.data.models.User;

import java.util.Date;
import java.util.List;

public class CommentServiceModel extends BaseServiceModel {

    private String description;
    private Date commentDate;
    private List<UserServiceModel> userComment;
    private List<PostServiceModel> postComment;
    private List<GroupServiceModel> groupComments;


    public CommentServiceModel() {
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

    public List<UserServiceModel> getUserComment() {
        return this.userComment;
    }

    public void setUserComment(List<UserServiceModel> userComment) {
        this.userComment = userComment;
    }

    public List<PostServiceModel> getPostComment() {
        return this.postComment;
    }

    public void setPostComment(List<PostServiceModel> postComment) {
        this.postComment = postComment;
    }

    public List<GroupServiceModel> getGroupComments() {
        return this.groupComments;
    }

    public void setGroupComments(List<GroupServiceModel> groupComments) {
        this.groupComments = groupComments;
    }
}
