package net.cookingbook.service;

import net.cookingbook.service.models.services.CommentServiceModel;

import java.util.List;

public interface CommentService {

    CommentServiceModel createComment(CommentServiceModel commentServiceModel);

    CommentServiceModel findById(String id);

    List<CommentServiceModel> findAllByPostId(String id);

    List<CommentServiceModel> findAllByUserId(String id);

    void deleteComment(String id);

    CommentServiceModel editComment(String id, CommentServiceModel commentServiceModel);
}
