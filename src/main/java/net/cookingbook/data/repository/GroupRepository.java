package net.cookingbook.data.repository;

import net.cookingbook.data.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String > {

    Group findGroupById(String id);

    List<Group> findAllByPosts_IdContains(String id);

    Group findByUsers_IdContains(String id);

    List<Group> findAllByUsers_IdContains(String id);
}
