package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.Message;
import net.cookingbook.data.models.User;
import net.cookingbook.data.models.UserProfile;
import net.cookingbook.data.repository.MessageRepository;
import net.cookingbook.data.repository.UserProfileRepository;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.service.RoleService;
import net.cookingbook.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final MessageRepository messageRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository, MessageRepository messageRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.messageRepository = messageRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        this.roleService.seedRolesInDb();
        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());

            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }


        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        this.modelMapper.map(this.userProfileRepository.saveAndFlush(userProfile), UserServiceModel.class);
        this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findUserById(id);
        UserProfile userProfile = this.userProfileRepository.findByUserId(id);
        List<Message> messages = this.messageRepository.findAllBySender_IdContains(id);
        List<User> allByFriendsIdContains = this.userRepository.findAllByFriendsIdContains(id);

        if (!allByFriendsIdContains.isEmpty()) {

            for (User allByFriendsIdContain : allByFriendsIdContains) {
                allByFriendsIdContain.getFriends().remove(user);
            }
            user.getFriends().removeAll(allByFriendsIdContains);
            user = this.userRepository.saveAndFlush(this.modelMapper.map(user, User.class));
        }

        if (!messages.isEmpty()) {
            this.messageRepository.deleteAll(messages);
        }

        user.getAuthorities().remove(user);
        this.userProfileRepository.delete(userProfile);
        this.userRepository.delete(user);
    }

    @Override
    public UserServiceModel findById(String id) {
        return this.userRepository.findById(id)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public void editUserProfile(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

        if (userServiceModel.getPassword() != null) {
            user.setPassword(userServiceModel.getPassword() != null ?
                    this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                    user.getPassword());
        }

        UserProfile userProfile = this.userProfileRepository.findByUserId(user.getId());
        if (!userServiceModel.getFirstName().isEmpty()) {
            userProfile.setFirstName(userServiceModel.getFirstName());
        }
        if (!userServiceModel.getLastName().isEmpty()) {
            userProfile.setLastName(userServiceModel.getLastName());
        }
        if (userServiceModel.getImageUrl() != null) {
            userProfile.setImageUrl(userServiceModel.getImageUrl());
        }

        this.modelMapper.map(this.userProfileRepository.saveAndFlush(userProfile), UserServiceModel.class);
        this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll().stream().map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public boolean addFriends(UserServiceModel userServiceModel) {
        User user = this.userRepository.save(this.modelMapper.map(userServiceModel, User.class));

        return user != null;
    }

    @Override
    public boolean isFriend(String id, String user_id) {
        var friend = this.userRepository.findUserById(id);
        var user = this.userRepository.findByIdAndFriendsContains(user_id, friend);
        return user != null;
    }

    @Override
    public boolean deleteFollow(String friend_id, UserServiceModel userServiceModel) {
        User user = this.userRepository.findUserById(userServiceModel.getId());
        User friend = userRepository.findUserById(friend_id);
        boolean remove = user.getFriends().remove(friend);

        if (remove) {
            user = this.userRepository.saveAndFlush(this.modelMapper.map(user, User.class));
            return true;
        }

        return false;
    }

    @Override
    public boolean isPresent(UserServiceModel userServiceModel) {

        if (this.userRepository.findByUsername(userServiceModel.getUsername()).isPresent()
                || this.userRepository.findByUsername(userServiceModel.getEmail()).isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public void setUserRole(String id, String role) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect id!"));

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        userServiceModel.getAuthorities().clear();

        switch (role) {
            case "user":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                break;
            case "moderator":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                break;
            case "admin":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));
                break;
        }

        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
    }
}
