package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.Post;
import net.cookingbook.data.models.Rate;
import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.data.repository.RateRepository;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.service.RateService;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.RateServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository, PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.rateRepository = rateRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(RateServiceModel rateServiceModel, PostServiceModel postServiceModel) {

        Post post = this.modelMapper.map(this.postRepository.findByImageUrl(postServiceModel.getImageUrl()), Post.class);
        Rate rate = this.modelMapper.map(rateServiceModel, Rate.class);

        rate.setPost(post);
        this.modelMapper.map(this.rateRepository.saveAndFlush(rate), RateServiceModel.class);

        post.setRate(rate);
        this.modelMapper.map(this.postRepository.saveAndFlush(post), PostServiceModel.class);
    }

    @Override
    public RateServiceModel rate(String post_id, String user_id) {
        User user = this.userRepository.findUserById(user_id);
        Rate rate = this.rateRepository.findByPost_idContains(post_id);

            rate.setCount(rate.getCount() + 1);
            rate.getUser().add(user);

        return this.modelMapper.map(this.rateRepository.saveAndFlush(rate), RateServiceModel.class);
    }

    @Override
    public boolean userHasRated(String rate_id, String user_id) {
        User user = this.userRepository.findUserById(user_id);
        Rate rate = this.rateRepository.findByIdAndUserContains(rate_id, user);

        return rate != null;
    }

    @Override
    public RateServiceModel findRateByPostId(String id) {
        Rate rate = this.rateRepository.findByPost_idContains(id);

        return this.modelMapper.map(rate, RateServiceModel.class);
    }
}
