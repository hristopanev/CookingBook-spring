package net.cookingbook.data.repository;

import net.cookingbook.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findUserById(String id);

    User findByFriends_IdContains(String id);

    User findByIdAndFriendsContains(String user_id, User user);

    boolean deleteByFriends_IdContains(String friend_id);

    List<User> findAllByFriendsIdContains(String id);
}
