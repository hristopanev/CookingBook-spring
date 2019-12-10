package net.cookingbook.web.view.controllers;

import net.cookingbook.service.GroupService;
import net.cookingbook.service.UserService;
import net.cookingbook.service.models.services.GroupServiceModel;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.PostService;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.web.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    private final PostService postService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private GroupService groupService;

    @Autowired
    public HomeController(PostService postService, UserService userService, ModelMapper modelMapper, GroupService groupService) {
        super(userService);
        this.postService = postService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.groupService = groupService;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index() {
        return super.view("index");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(Principal principal, ModelAndView modelAndView) {
        var user = getUsername(principal);

        List<UserServiceModel> friends = this.userService.findById(user.getId())
                .getFriends()
                .stream()
                .map(f -> this.modelMapper.map(f, UserServiceModel.class))
                .collect(Collectors.toList());
        List<GroupServiceModel> usersGroups = this.groupService.getAllUsersGroups(user);

        List<PostServiceModel> posts = this.postService.findAllPosts();


        modelAndView.addObject("friends", friends);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("userID", user.getId());
        modelAndView.addObject("usersGroups", usersGroups);
        return super.view("home", modelAndView);
    }
}
