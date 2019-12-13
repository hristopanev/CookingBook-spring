package net.cookingbook.service;

import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.RateServiceModel;

import java.util.List;

public interface PostService {

    PostServiceModel createPost(PostServiceModel postServiceModel);

    List<PostServiceModel> findAllPosts();

    PostServiceModel editPost(String id, PostServiceModel postServiceModel);

    PostServiceModel findById(String id);

    void deletePost(String id);

    List<PostServiceModel> getAllUserPosts(String id);

    boolean isExist(String id);

    List<PostServiceModel> findAllGroupPosts(String id);
}
