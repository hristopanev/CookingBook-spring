package net.cookingbook.service;

import net.cookingbook.service.models.services.SavedRecipeServiceModel;

import java.util.List;

public interface SavedRecipeService {

    void saveRecipe(SavedRecipeServiceModel savedRecipeServiceModel);

    void delete(String id);

    List<SavedRecipeServiceModel> findAll(String id);
}
