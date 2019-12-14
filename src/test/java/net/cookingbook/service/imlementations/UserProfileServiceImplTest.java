package net.cookingbook.service.imlementations;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.models.User;
import net.cookingbook.data.models.UserProfile;
import net.cookingbook.data.repository.UserProfileRepository;
import net.cookingbook.service.UserProfileService;
import net.cookingbook.service.models.services.UserProfileServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileServiceImplTest extends TestBase {

    @MockBean
    UserProfileRepository userProfileRepository;

    @MockBean
    UserProfileService userProfileService;

    @Autowired
    UserProfileService service;

    @Test
    void getUserProfile_whenNoUserProfile_shouldReturnEmptyList() {
        List<UserProfileServiceModel> userProfileServiceModels = service.findALl();
        assertEquals(0, userProfileServiceModels.size());
    }

    @Test
    void getUserProfileByUser_whenUserProfile_shouldReturnUserId() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId("1");

        User user = new User();
        user.setId("2");

        userProfile.setUser(user);
        this.userProfileRepository.saveAndFlush(userProfile);

        assertEquals("2", userProfile.getUser().getId());


    }
}