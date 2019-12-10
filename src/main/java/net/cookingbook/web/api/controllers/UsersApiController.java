package net.cookingbook.web.api.controllers;

import net.cookingbook.service.GroupService;
import net.cookingbook.service.UserService;
import net.cookingbook.web.api.models.UserResponseModel;
import net.cookingbook.web.api.models.UsersGroupsResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersApiController {
    private final UserService userService;
    private final GroupService groupService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersApiController(UserService userService, GroupService groupService, GroupService groupService1, ModelMapper modelMapper) {
        this.userService = userService;
        this.groupService = groupService1;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/api/users")
    public List<UserResponseModel> getGroups(HttpSession session) {
        List<UserResponseModel> collect = userService.findAllUsers()
                .stream()
                .map(u -> this.modelMapper.map(u, UserResponseModel.class))
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping(value = "/api/userGroups")
    public List<UsersGroupsResponseModel> getAllUsersGroups(HttpSession session, Principal principal) {
        var user = this.userService.findUserByUserName(principal.getName());

        List<UsersGroupsResponseModel> collect = this.groupService.getAllUsersGroups(user)
                .stream()
                .map(g -> this.modelMapper.map(g, UsersGroupsResponseModel.class))
                .collect(Collectors.toList());
        return collect;
    }
}
