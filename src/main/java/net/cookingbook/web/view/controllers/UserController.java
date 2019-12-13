package net.cookingbook.web.view.controllers;

import net.cookingbook.errors.UserNotFoundException;
import net.cookingbook.service.*;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.web.base.BaseController;
import net.cookingbook.web.view.models.binding.UserEditBindingModel;
import net.cookingbook.web.view.models.views.UserAllViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    public final static String USER_DETAILS_VIEW = "users/user-details";

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final PostService postService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, UserProfileService userProfileService, PostService postService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        super(userService);
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.postService = postService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView userProfile(Principal principal, ModelAndView modelAndView) {

        var user = getUsername(principal);
        var userProfile = this.userProfileService.findByUserId(user.getId());
        List<PostServiceModel> userPosts = this.postService.getAllUserPosts(user.getId());

        modelAndView.addObject("userPosts", userPosts);
        modelAndView.addObject("userID", user.getId());
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("profileImage", userProfile.getImageUrl());
        return super.view("users/profile", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView userProfileDetails(Principal principal, @PathVariable String id, ModelAndView modelAndView) {

        UserServiceModel userServiceModel = this.userService.findById(id);
        var userProfile = this.userProfileService.findByUserId(id);
        var user = getUsername(principal);

        var isFriends = this.userService.isFriend(id, user.getId());
        List<PostServiceModel> userDetails = this.postService.getAllUserPosts(id);
        modelAndView.addObject("isFriends", isFriends);
        modelAndView.addObject("userDetails", userDetails);
        modelAndView.addObject("userID", id);
        modelAndView.addObject("userProfile", userServiceModel.getUsername());
        modelAndView.addObject("username", principal.getName());
        modelAndView.addObject("profileImage", userProfile.getImageUrl());

        return super.view(USER_DETAILS_VIEW, modelAndView);
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView) {
        var user = getUsername(principal);
        UserServiceModel userServiceModel = this.modelMapper.map(this.userProfileService.findByUserId(user.getId()), UserServiceModel.class);
        modelAndView.addObject("model", userServiceModel);
        modelAndView.addObject("username", user.getUsername());

        return super.view("users/edit", modelAndView);
    }


    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(Principal principal, @ModelAttribute(name = "model") UserEditBindingModel model, BindingResult bindingResult) throws IOException {

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        userServiceModel.setUsername(principal.getName());
        if (model.getPassword() != null && model.getConfirmPassword() != null
                && model.getPassword().equals(model.getConfirmPassword())) {
            userServiceModel.setPassword(model.getPassword());
        }

        if (!model.getImage().isEmpty()) {
            userServiceModel.setImageUrl(this.cloudinaryService.uploadImage(model.getImage()));
        }

        this.userService.editUserProfile(userServiceModel);

        return super.redirect("profile");
    }

    @PostMapping("/follow/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addFollowConfig(Principal principal, @PathVariable String id) {
        UserServiceModel loggedUser = this.userService.findUserByUserName(principal.getName());
        UserServiceModel user = this.userService.findById(id);

        loggedUser.getFriends().add(user);
        if (!this.userService.addFriends(loggedUser)) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        return super.redirect("/users/details/" + id);
    }

    @PostMapping("/delete-follow/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteFollowConfig(@PathVariable String id, Principal principal) {
        var user = getUsername(principal);
        userService.deleteFollow(id, user);

        return super.redirect("/users/details/" + id);
    }

    @GetMapping("/friends")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAllFriends(Principal principal,  ModelAndView modelAndView) {

        var user = getUsername(principal);
        List<UserServiceModel> friends = this.userService.findById(user.getId())
                .getFriends()
                .stream()
                .map(f -> this.modelMapper.map(f, UserServiceModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("friends", friends);
        modelAndView.addObject("username", user.getUsername());

        return super.view("users/friends", modelAndView);
    }

    @GetMapping("/groups")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAllUserGroups(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("username", principal.getName());
        return super.view("users/groups", modelAndView);
    }

    @PostMapping("/set-user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setUser(@PathVariable String id) {
        this.userService.setUserRole(id, "user");

        return super.redirect("/users/all-users");
    }

    @PostMapping("/set-moderator/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setModerator(@PathVariable String id) {
        this.userService.setUserRole(id, "moderator");

        return super.redirect("/users/all-users");
    }

    @PostMapping("/set-admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setAdmin(@PathVariable String id) {
        this.userService.setUserRole(id, "admin");

        return super.redirect("/users/all-users");
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView getAllUser(ModelAndView modelAndView, Principal principal) {
        List<UserAllViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAllViewModel user = this.modelMapper.map(u, UserAllViewModel.class);
                    Set<String> authorities = u.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet());
                    user.setAuthorities(authorities);
                    return user;
                })
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);
        modelAndView.addObject("username", principal.getName());

        return super.view("users/all-users", modelAndView);
    }

    @GetMapping("/my-posts")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getUsersPosts(Principal principal, ModelAndView modelAndView) {
        var user = getUsername(principal);
        List<PostServiceModel> userPosts = this.postService.getAllUserPosts(user.getId());

        modelAndView.addObject("userPosts", userPosts);
        modelAndView.addObject("username", user.getUsername());
        return super.view("users/my-posts", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteMessage(@PathVariable String id) {
        this.userService.deleteUser(id);

        return super.redirect("/users/all-users");
    }


    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView handleException(UserNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
