package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.Comment;
import net.cookingbook.data.models.Post;
import net.cookingbook.data.models.Rate;
import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.CommentRepository;
import net.cookingbook.data.repository.RateRepository;
import net.cookingbook.errors.PostNotFoundException;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final RateRepository rateRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, RateRepository rateRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.rateRepository = rateRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostServiceModel createPost(PostServiceModel postServiceModel) {
        Post post = this.modelMapper.map(postServiceModel, Post.class);
        return this.modelMapper.map(this.postRepository.saveAndFlush(post), PostServiceModel.class);
    }

    @Override
    public List<PostServiceModel> findAllPosts() {
        return this.postRepository.findAllAndOrderByPostTimeDesc()
                .stream()
                .map(post -> this.modelMapper.map(post, PostServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostServiceModel editPost(String id, PostServiceModel postServiceModel) {
        Post post = this.postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with the given id was not found"));

        post.setName(postServiceModel.getName());

        if (postServiceModel.getImageUrl() != null) {
            post.setImageUrl(postServiceModel.getImageUrl());
        }
        post.setProducts(postServiceModel.getProducts());
        post.setDescription(postServiceModel.getDescription());

        return this.modelMapper.map(this.postRepository.saveAndFlush(post), PostServiceModel.class);
    }

    @Override
    public PostServiceModel findById(String id) {
        Post post = this.postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with the given id was not found"));

        return this.modelMapper.map(post, PostServiceModel.class);
    }

    @Override
    public void deletePost(String id) {
        Post post = this.postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with the given id was not found"));
        Rate rate = this.rateRepository.findByPost_idContains(id);
        Comment comment = this.commentRepository.findByPostComment_idContains(post.getId());

        this.commentRepository.delete(comment);
        this.rateRepository.delete(rate);
        this.postRepository.delete(post);
    }

    @Override
    public List<PostServiceModel> getAllUserPosts(String id) {
        return this.postRepository.findByUploader_IdContainsOrderByPostTimeDesc(id)
                .stream()
                .map(p -> this.modelMapper.map(p, PostServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExist(String id) {
        Post post = this.postRepository.findPostById(id);

        return post != null;
    }
}
