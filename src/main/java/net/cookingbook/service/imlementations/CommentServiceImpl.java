package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.Comment;
import net.cookingbook.data.models.Post;
import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.CommentRepository;
import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.service.CommentService;
import net.cookingbook.service.models.services.CommentServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentServiceModel createComment(CommentServiceModel commentServiceModel) {
        Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);

        return this.modelMapper.map(this.commentRepository.save(comment), CommentServiceModel.class);
    }

    @Override
    public CommentServiceModel findById(String id) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        return this.modelMapper.map(comment, CommentServiceModel.class);
    }

    @Override
    public List<CommentServiceModel> findAllByPostId(String id) {
        Post post = this.postRepository.findPostById(id);
        List<Comment> comment = this.commentRepository.findAllByPostCommentContains(post);

        return comment
                .stream()
                .map(c -> this.modelMapper.map(c, CommentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentServiceModel> findAllByUserId(String id) {
        User user = this.userRepository.findUserById(id);
        List<Comment> comment = this.commentRepository.findAllByUserCommentContains(user);

        return comment
                .stream()
                .map(c -> this.modelMapper.map(c, CommentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(String id) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        this.commentRepository.delete(comment);
    }

    @Override
    public CommentServiceModel editComment(String id, CommentServiceModel commentServiceModel) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        if (!commentServiceModel.getDescription().isEmpty()) {
            comment.setDescription(commentServiceModel.getDescription());
        }
        return this.modelMapper.map(this.commentRepository.saveAndFlush(comment), CommentServiceModel.class);
    }
}
