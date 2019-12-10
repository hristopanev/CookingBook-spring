package net.cookingbook.data.repository;

import net.cookingbook.data.models.SavedRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedRecipeRepository extends JpaRepository<SavedRecipe, String> {

    List<SavedRecipe> findByUser_IdContains(String id);
}
