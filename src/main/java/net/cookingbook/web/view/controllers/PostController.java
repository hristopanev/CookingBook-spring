package net.cookingbook.web.view.controllers;

import net.cookingbook.errors.PostNotFoundException;
import net.cookingbook.service.RateService;
import net.cookingbook.service.models.services.CommentServiceModel;
import net.cookingbook.service.models.services.RateServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.web.base.BaseController;
import net.cookingbook.web.view.models.binding.PostCreateBindingModel;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.CloudinaryService;
import net.cookingbook.service.PostService;
import net.cookingbook.service.UserService;
import net.cookingbook.web.view.models.binding.RateCreateBindingModel;
import net.cookingbook.web.view.models.views.PostDetailsViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {

    private final PostService postService;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;
    private final RateService rateService;
    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, CloudinaryService cloudinaryService, UserService userService, RateService rateService, ModelMapper modelMapper) {
        super(userService);
        this.postService = postService;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
        this.rateService = rateService;
        this.modelMapper = modelMapper;
    }

//    @ModelAttribute()
//    public PostCreateBindingModel model() {
//        return new PostCreateBindingModel();
//    }

    @GetMapping("/add")
    public ModelAndView addPost(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("username", principal.getName());

        return super.view("posts/add-post", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView addProductConfirm(@ModelAttribute PostCreateBindingModel model, Principal principal) throws IOException {
        PostServiceModel postServiceModel = this.modelMapper.map(model, PostServiceModel.class);
        UserServiceModel user = this.userService.findUserByUserName(principal.getName());
        RateServiceModel rateServiceModel = new RateServiceModel();

        postServiceModel.setPostTime(new Date());
        postServiceModel.setImageUrl(this.cloudinaryService.uploadImage(model.getImage()));
        postServiceModel.setUploader(user);
        this.postService.createPost(postServiceModel);
        this.rateService.create(rateServiceModel, postServiceModel);

        return super.redirect("/home");
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView editPost(Principal principal, @PathVariable String id, ModelAndView modelAndView) {
        PostServiceModel postServiceModel = this.postService.findById(id);

        modelAndView.addObject("post", postServiceModel);
        modelAndView.addObject("postId", id);
        modelAndView.addObject("username", principal.getName());

        return super.view("posts/edit-post", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public  ModelAndView editPostConfirm(@PathVariable String id, @ModelAttribute PostCreateBindingModel model) throws IOException {
        PostServiceModel postServiceModel = this.modelMapper.map(model, PostServiceModel.class);

        if (model.getImage().getBytes().length != 0) {
            postServiceModel.setImageUrl(this.cloudinaryService.uploadImage(model.getImage()));
        }

        this.postService.editPost(id, postServiceModel);
        return super.redirect("/posts/details/" + id);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView deletePost(Principal principal, @PathVariable String id, ModelAndView modelAndView) {
        PostServiceModel postServiceModel = this.postService.findById(id);

        modelAndView.addObject("post", postServiceModel);
        modelAndView.addObject("postId", id);
        modelAndView.addObject("username", principal.getName());

        return super.view("posts/delete-post", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView deletePostConfirm(@PathVariable String id) {
        this.postService.deletePost(id);

        return super.redirect("/home");
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView detailsPost(Principal principal, @PathVariable String id, ModelAndView modelAndView) {
        var user = getUsername(principal);
        PostDetailsViewModel model = this.modelMapper.map(this.postService.findById(id),
                PostDetailsViewModel.class);

        List<CommentServiceModel> postComment = this.postService.findById(id)
                .getComments()
                .stream()
                .map(c -> this.modelMapper.map(c, CommentServiceModel.class))
                .collect(Collectors.toList());
        RateServiceModel rateByPostId = this.rateService.findRateByPostId(id);
        boolean isVoted = this.rateService.userHasRated(rateByPostId.getId(), user.getId());

        modelAndView.addObject("isVoted", isVoted);
        modelAndView.addObject("postComment", postComment);
        modelAndView.addObject("post", model);
        modelAndView.addObject("postID", id);
        modelAndView.addObject("username", user.getUsername());

        return super.view("posts/details-post", modelAndView);
    }

    @GetMapping("/all-posts")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAllPosts(ModelAndView modelAndView, Principal principal) {
        var user = this.userService.findUserByUserName(principal.getName());
        List<PostServiceModel> posts = this.postService.findAllPosts();
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("username", user.getUsername());
        return super.view("posts/all-posts", modelAndView);
    }

    @ExceptionHandler({PostNotFoundException.class})
    public ModelAndView handleProductNotFound(PostNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }
}
