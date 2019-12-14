package net.cookingbook.service.imlementations;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.models.SavedRecipe;
import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.SavedRecipeRepository;
import net.cookingbook.service.SavedRecipeService;
import net.cookingbook.service.models.services.SavedRecipeServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SavedRecipeImplTest extends TestBase {
    @MockBean
    SavedRecipeRepository savedRecipeRepository;

    @MockBean
    SavedRecipeService savedRecipeService;

    @Autowired
    SavedRecipeService service;

    @Test
    void getSavedRecipeForUser_whenNoSavedRecipe_shouldReturnEmptyList() {
        List<SavedRecipeServiceModel> savedRecipeServiceModels = service.findAll("1");
        assertEquals(0, savedRecipeServiceModels.size());
    }

    @Test
    void getSavedRecipeByUser_whenSavedRecipe_shouldReturnUserId() {
        SavedRecipe recipe = new SavedRecipe();
        recipe.setId("1");

        User user = new User();
        user.setId("2");

        recipe.setUser(user);
        this.savedRecipeRepository.saveAndFlush(recipe);

        assertEquals("2", recipe.getUser().getId());

    }
}