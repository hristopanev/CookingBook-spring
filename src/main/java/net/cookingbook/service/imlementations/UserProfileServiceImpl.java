package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.UserProfile;
import net.cookingbook.data.repository.UserProfileRepository;
import net.cookingbook.service.UserProfileService;
import net.cookingbook.service.models.services.UserProfileServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileServiceModel findById(String id) {
        return this.repository.findById(id)
                .map(u -> this.modelMapper.map(u, UserProfileServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserProfile findByUserId(String id) {
        return this.repository.findByUserId(id);
    }

    @Override
    public List<UserProfileServiceModel> findALl() {
        return this.repository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserProfileServiceModel.class))
                .collect(Collectors.toList());
    }
}
