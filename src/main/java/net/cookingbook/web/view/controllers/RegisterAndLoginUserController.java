package net.cookingbook.web.view.controllers;

import net.cookingbook.validation.user.UserRegisterValidator;
import net.cookingbook.web.base.BaseController;
import net.cookingbook.web.view.models.binding.UserRegisterBindingModel;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegisterAndLoginUserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private UserRegisterValidator userRegisterValidator;

    @Autowired
    public RegisterAndLoginUserController(UserService userService, ModelMapper modelMapper, UserRegisterValidator userRegisterValidator) {
        super(userService);
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = userRegisterValidator;
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model, BindingResult bindingResult) {

        boolean userIsValid = this.userService.isPresent(this.modelMapper.map(model, UserServiceModel.class));

        if (!userIsValid) {
            return super.view("/users/user-register");
        }

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("users/user-register");
        }
        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);


        return super.redirect("users/login");
    }

    @GetMapping("/user-register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView userRegister(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("/users/user-register");
    }

    @PostMapping("/user-register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerIncorrect(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model, BindingResult bindingResult) {

        this.userRegisterValidator.validate(model, bindingResult);
        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);

            return super.view("/users/user-register", modelAndView);
        }
        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);


        return super.redirect("users/login");
    }


    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return super.view("/users/login");
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm() {
        return super.redirect("/home");
    }


    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
