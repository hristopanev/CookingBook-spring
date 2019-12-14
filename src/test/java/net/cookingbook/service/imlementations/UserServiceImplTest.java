package net.cookingbook.service.imlementations;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.service.UserService;
import net.cookingbook.service.models.services.UserServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends TestBase {
    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    @Autowired
    UserService service;

    @Test
    void getUser_whenNoUser_shouldReturnEmptyList() {
        List<UserServiceModel> userServiceModels = service.findAllUsers();
        assertEquals(0, userServiceModels.size());
    }

    @Test
    void getUser_whenUser_shouldReturnUserId() {
        User user = new User();
        user.setId("1");
        this.userRepository.saveAndFlush(user);

        assertEquals("1", user.getId());
    }
}