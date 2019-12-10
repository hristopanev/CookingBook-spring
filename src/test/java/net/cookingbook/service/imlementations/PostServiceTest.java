package net.cookingbook.service.imlementations;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.repository.PostRepository;
import net.cookingbook.service.PostService;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest extends TestBase {

    @MockBean
    PostRepository postRepository;

    @MockBean
    PostService postService;

    @Autowired
    PostService service;

    @Test
    void getPostForUser_whenNoPost_shouldReturnEmptyList() {
        List<PostServiceModel> postServiceModels = service.getAllUserPosts("1");
        assertEquals(0, postServiceModels.size());
    }

    @Test
    void getPostForUser_whenPost_shouldReturnUploader() {
        UserServiceModel user = new UserServiceModel();
        user.setId("1");

        PostServiceModel post = new PostServiceModel();
        post.setUploader(user);
        service.createPost(post);

        assertEquals(user.getId(), post.getUploader().getId());

    }

}