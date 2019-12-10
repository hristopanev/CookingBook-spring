package net.cookingbook.data.repository;

import net.cookingbook.data.models.Comment;
import net.cookingbook.data.models.Post;
import net.cookingbook.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String > {

    List<Comment> findAllByPostCommentContains(Post post);

    List<Comment> findAllByUserCommentContains(User user);

    Comment findByPostComment_idContains(String id);
}
