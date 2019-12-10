package net.cookingbook.data.repository;

import net.cookingbook.data.models.Note;
import net.cookingbook.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, String> {

    List<Note> findAllByUser_IdContains(String id);
}
