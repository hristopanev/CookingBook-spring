package net.cookingbook.web.view.controllers;

import net.cookingbook.service.PostService;
import net.cookingbook.service.SavedRecipeService;
import net.cookingbook.service.UserService;
import net.cookingbook.service.models.services.SavedRecipeServiceModel;
import net.cookingbook.web.base.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/saved")
public class SavedRecipeController extends BaseController {

    private final SavedRecipeService savedRecipeService;
    private final PostService postService;

    public SavedRecipeController(UserService userService, SavedRecipeService savedRecipeService, PostService postService) {
        super(userService);
        this.savedRecipeService = savedRecipeService;
        this.postService = postService;
    }

    @PostMapping("/save/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView saveRecipe(@PathVariable String id, Principal principal, @ModelAttribute SavedRecipeServiceModel model) {
        var post = this.postService.findById(id);
        var user = getUsername(principal);
        model.setUser(user);
        model.setPost(post);

        this.savedRecipeService.saveRecipe(model);

        return super.redirect("/saved/all");
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAll(Principal principal, ModelAndView modelAndView) {
        var user = getUsername(principal);
        List<SavedRecipeServiceModel> all = this.savedRecipeService.findAll(user.getId());

        modelAndView.addObject("recipes", all);
        modelAndView.addObject("username", user.getUsername());

        return super.view("saved/all", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView delete(@PathVariable String id) {
        this.savedRecipeService.delete(id);

        return super.redirect("/saved/all");
    }
}
