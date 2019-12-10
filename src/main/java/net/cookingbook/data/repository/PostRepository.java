package net.cookingbook.data.repository;

import net.cookingbook.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String > {

    Optional<Post> findByName(String name);

    @Query("SELECT p FROM Post p ORDER BY p.postTime DESC")
    List<Post> findAllAndOrderByPostTimeDesc();

    List<Post> findByUploader_IdContainsOrderByPostTimeDesc(String id);

    Post findByImageUrl(String imageUrl);

    Post findPostById(String id);
}
