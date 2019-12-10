package net.cookingbook.web.view.controllers;

import net.cookingbook.service.CommentService;
import net.cookingbook.service.GroupService;
import net.cookingbook.service.PostService;
import net.cookingbook.service.UserService;
import net.cookingbook.service.models.services.CommentServiceModel;
import net.cookingbook.web.base.BaseController;
import net.cookingbook.web.view.models.binding.CommentCreateBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/comments")
public class CommentController extends BaseController {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final GroupService groupService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService, UserService userService, ModelMapper modelMapper, GroupService groupService) {
        super(userService);
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.groupService = groupService;
    }

    @PostMapping("/comment-post/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createComment(@PathVariable String id, @ModelAttribute CommentCreateBindingModel model, Principal principal) {
        CommentServiceModel commentServiceModel = this.modelMapper.map(model, CommentServiceModel.class);

        var user = getUsername(principal);
        model.setUserComment(user);

        commentServiceModel.setUserComment(
                this.userService.findAllUsers()
                        .stream()
                        .filter(u -> model.getUserComment().getId().contains(u.getId()))
                        .collect(Collectors.toList())
        );
        commentServiceModel.setCommentDate(new Date());

        // SET POST IF COMMENT IN POST IS
        if (this.postService.isExist(id)) {
            var post = this.postService.findById(id);
            model.setPostComment(post);

            commentServiceModel.setPostComment(
                    this.postService.findAllPosts()
                            .stream()
                            .filter(p -> model.getPostComment().getId().contains(p.getId()))
                            .collect(Collectors.toList())
            );
            this.commentService.createComment(commentServiceModel);

            return super.redirect("/posts/details/" +id);
        }

        if (groupService.isExist(id)) {
            var group = this.groupService.findById(id);
            model.setGroupComments(group);

            commentServiceModel.setGroupComments(
                    this.groupService.findAllGroups()
                    .stream()
                    .filter(g -> model.getGroupComments().getId().contains(g.getId()))
                    .collect(Collectors.toList())
            );

            return super.redirect("groups/group/" + id);
        }

        return super.redirect("users/user-details/" + user.getId());
    }
}
