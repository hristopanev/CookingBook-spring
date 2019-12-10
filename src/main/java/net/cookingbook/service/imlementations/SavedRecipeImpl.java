package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.SavedRecipe;
import net.cookingbook.data.repository.SavedRecipeRepository;
import net.cookingbook.service.SavedRecipeService;
import net.cookingbook.service.models.services.SavedRecipeServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedRecipeImpl implements SavedRecipeService {

    private final SavedRecipeRepository savedRecipeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SavedRecipeImpl(SavedRecipeRepository savedRecipeRepository, ModelMapper modelMapper) {
        this.savedRecipeRepository = savedRecipeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveRecipe(SavedRecipeServiceModel savedRecipeServiceModel) {
        SavedRecipe savedRecipe = this.modelMapper.map(savedRecipeServiceModel, SavedRecipe.class);
        this.savedRecipeRepository.saveAndFlush(savedRecipe);
    }

    @Override
    public void delete(String id) {
        SavedRecipe savedRecipe = this.savedRecipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recipe with the given id was not found"));
        this.savedRecipeRepository.delete(savedRecipe);
    }

    @Override
    public List<SavedRecipeServiceModel> findAll(String id) {
        return this.savedRecipeRepository.findByUser_IdContains(id)
                .stream()
                .map(r -> this.modelMapper.map(r, SavedRecipeServiceModel.class))
                .collect(Collectors.toList());
    }
}
