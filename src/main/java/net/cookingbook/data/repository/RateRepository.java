package net.cookingbook.data.repository;

import net.cookingbook.data.models.Post;
import net.cookingbook.data.models.Rate;
import net.cookingbook.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, String> {

    Rate findByIdAndUserContains(String id, User user);

    Rate findByPost_idContains(String id);

    Rate findByPost(Post post);

    List<Rate> findAllByUser_IdContains(String id);
}
