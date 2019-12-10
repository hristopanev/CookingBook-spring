package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.Group;
import net.cookingbook.data.repository.GroupRepository;
import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.service.models.services.GroupServiceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;


import static org.junit.jupiter.api.Assertions.*;


class GroupServiceTest {
    GroupRepository groupRepository;
    UserRepository userRepository;
    PostRepository postRepository;
    ModelMapper modelMapper;

    GroupServiceImpl service;

    @BeforeEach
    void setupTest() {
        groupRepository = Mockito.mock(GroupRepository.class);
        modelMapper = new ModelMapper();

        service = new GroupServiceImpl(groupRepository, userRepository, postRepository, modelMapper);
    }

    @Test
    void getById_whenGNoGroup() {
        String groupId = "1";

        Mockito.when(groupRepository.findGroupById(groupId))
                .thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> service.findById(groupId));
    }

    @Test
    void getById_whenGroupDoesNotExits() {
        String groupId = "1";

        Group group = new Group();
        group.setName("Name");
        group.setId(groupId);
        group.setDescription("Description");

        Mockito.when(groupRepository.findGroupById(groupId))
                .thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> service.findById(groupId));
    }

    @Test
    void createGroup_WhenIsValid_showGroupName() {
        String groupName = "Name";
        GroupServiceModel groupServiceModel = new GroupServiceModel();
        groupServiceModel.setName("Name");

        service.createGroup(groupServiceModel);

        assertEquals(groupName, groupServiceModel.getName());
    }
}