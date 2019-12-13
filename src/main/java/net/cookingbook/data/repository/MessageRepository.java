package net.cookingbook.data.repository;

import net.cookingbook.data.models.Message;
import net.cookingbook.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String > {

    List<Message> findAllByUser_IdContains(String id);
}
