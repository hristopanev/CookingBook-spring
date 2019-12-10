package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.Group;
import net.cookingbook.data.models.Post;
import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.GroupRepository;
import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.service.GroupService;
import net.cookingbook.service.models.services.GroupServiceModel;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GroupServiceModel createGroup(GroupServiceModel groupServiceModel) {

        Group group = this.modelMapper.map(groupServiceModel, Group.class);
        this.groupRepository.saveAndFlush(group);
        return this.modelMapper.map(group, GroupServiceModel.class);
    }

    @Override
    public List<GroupServiceModel> findAllGroups() {
        return this.groupRepository.findAll()
                .stream()
                .map(group -> this.modelMapper.map(group, GroupServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public GroupServiceModel findById(String id) {
        Group group = this.groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        return this.modelMapper.map(group, GroupServiceModel.class);
    }

    @Override
    public void deleteGroup(String id) {
        Group group = this.groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        this.groupRepository.delete(group);
    }

    @Override
    public GroupServiceModel editGroup(String id, GroupServiceModel groupServiceModel) {
        Group group = this.groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        if (groupServiceModel.getName() != null) {
            group.setName(groupServiceModel.getName());
        }
        if (groupServiceModel.getDescription() != null) {
            group.setDescription(groupServiceModel.getDescription());
        }

        return this.modelMapper.map(this.groupRepository.saveAndFlush(group), GroupServiceModel.class);
    }


    @Override
    public List<PostServiceModel> findAllPostByIdGroup(String id) {

        List<PostServiceModel> posts = this.groupRepository.findAll()
                .get(0).getPosts()
                .stream()
                .filter(p -> p.getId().contains(id))
                .map(p -> this.modelMapper.map(p, PostServiceModel.class))
                .collect(Collectors.toList());

        return posts;
    }

    @Override
    public boolean isJoined(String group_id, UserServiceModel user) {
        Optional<Group> group = groupRepository.findById(group_id);

        for (User u1 : group.get().getUsers()) {
            if (u1.getId().contains(user.getId())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean joinGroup(GroupServiceModel groupServiceModel) {
        Group group = this.groupRepository.save(this.modelMapper.map(groupServiceModel, Group.class));

        return group != null;
    }

    @Override
    public boolean leaveGroup(String user_id, String group_id) {
        Group group = groupRepository.findGroupById(group_id);
        User user = userRepository.findUserById(user_id);
        boolean remove = group.getUsers().remove(user);

        if (remove) {
            group = groupRepository.saveAndFlush(this.modelMapper.map(group, Group.class));
            return true;
        }

        return false;
    }

    @Override
    public List<GroupServiceModel> getAllUsersGroups(UserServiceModel userServiceModel) {

        List<GroupServiceModel> groups = this.groupRepository.findAllByUsers_IdContains(userServiceModel.getId())
                .stream()
                .map(g -> this.modelMapper.map(g, GroupServiceModel.class))
                .collect(Collectors.toList());

        return groups;
    }

    @Override
    public boolean cretePost(GroupServiceModel groupServiceModel, PostServiceModel postServiceModel) {
        Post post = this.postRepository.findByImageUrl(postServiceModel.getImageUrl());
        groupServiceModel.getPosts().add(this.modelMapper.map(post, PostServiceModel.class));
        Group group = this.groupRepository.save(this.modelMapper.map(groupServiceModel, Group.class));

        return group != null;
    }

    @Override
    public boolean isExist(String id) {
        Group group = this.groupRepository.findGroupById(id);

        return  group != null;
    }
}
