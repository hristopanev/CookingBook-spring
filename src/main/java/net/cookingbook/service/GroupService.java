package net.cookingbook.service;

import net.cookingbook.service.models.services.GroupServiceModel;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;

import java.util.List;

public interface GroupService {

    GroupServiceModel createGroup(GroupServiceModel groupServiceModel);

    List<GroupServiceModel> findAllGroups();

    GroupServiceModel findById(String id);

    void deleteGroup(String id);

    GroupServiceModel editGroup(String id, GroupServiceModel groupServiceModel);

    List<PostServiceModel> findAllPostByIdGroup(String id);

    boolean isJoined(String group_id, UserServiceModel user);

    boolean joinGroup(GroupServiceModel groupServiceModel);

    boolean leaveGroup(String user_id, String group_id);

    List<GroupServiceModel> getAllUsersGroups(UserServiceModel userServiceModel);

    boolean cretePost(GroupServiceModel groupServiceModel, PostServiceModel postServiceModel);

    boolean isExist(String id);

}
