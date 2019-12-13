package net.cookingbook.service;

import net.cookingbook.service.models.services.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findById(String id);

    UserServiceModel findUserByUserName(String username);

    void editUserProfile(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    void setUserRole(String id, String role);

    boolean addFriends(UserServiceModel userServiceModel);

    boolean isFriend(String id, String user_id);

    boolean deleteFollow(String friend_id, UserServiceModel userServiceModel);

    boolean isPresent(UserServiceModel userServiceModel);

    void deleteUser(String id);
}
