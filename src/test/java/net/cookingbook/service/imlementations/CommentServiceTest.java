package net.cookingbook.service.imlementations;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.repository.CommentRepository;
import net.cookingbook.service.CommentService;
import net.cookingbook.service.models.services.CommentServiceModel;
import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest extends TestBase {

    @MockBean
    CommentRepository commentRepository;

    @MockBean
    CommentService commentService;

    @Test
    void getCommentForUser_whenNoComment_shouldReturnEmptyList() {
        List<CommentServiceModel> postServiceModels = commentService.findAllByPostId("1");
        assertEquals(0, postServiceModels.size());
    }

    @Test
    void createComment_whenComment_shouldReturnCommentId() {
        String id = "1";

        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setId("1");
        commentService.createComment(commentServiceModel);

        assertEquals(id, commentServiceModel.getId());
    }
}