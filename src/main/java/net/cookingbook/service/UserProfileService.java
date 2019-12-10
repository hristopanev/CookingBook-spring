package net.cookingbook.service;

import net.cookingbook.data.models.UserProfile;
import net.cookingbook.service.models.services.UserProfileServiceModel;

import java.util.List;

public interface UserProfileService {

    UserProfileServiceModel findById(String id);

    UserProfile findByUserId(String id);

    List<UserProfileServiceModel> findALl();
}
