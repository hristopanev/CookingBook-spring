package net.cookingbook.web.base;

import net.cookingbook.service.UserService;
import net.cookingbook.service.models.services.UserServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

public abstract class BaseController {

    private final UserService userService;

    public BaseController(UserService userService) {
        this.userService = userService;
    }

    protected ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.setViewName(view);

        return modelAndView;
    }

    protected ModelAndView view(String view) {

        return this.view(view, new  ModelAndView());
    }

    protected ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }

    protected UserServiceModel getUsername(Principal principal) {
        return this.userService.findUserByUserName(principal.getName());
    }
}
