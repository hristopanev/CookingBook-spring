package net.cookingbook.web.view.controllers;

import net.cookingbook.validation.user.UserLoginValidator;
import net.cookingbook.validation.user.UserRegisterValidator;
import net.cookingbook.web.base.BaseController;
import net.cookingbook.web.view.models.binding.UserRegisterBindingModel;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegisterAndLoginUserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator userRegisterValidator;

    @Autowired
    public RegisterAndLoginUserController(UserService userService, ModelMapper modelMapper, UserRegisterValidator userRegisterValidator) {
        super(userService);
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = userRegisterValidator;
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model,  BindingResult bindingResult) {

        return getModelAndView(modelAndView, model, bindingResult);
    }

    @GetMapping("/user-register")
    public ModelAndView userRegister(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("users/user-register", modelAndView);
    }

    @PostMapping("/user-register")
    public ModelAndView registerIncorrect(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model, BindingResult bindingResult) {

        return getModelAndView(modelAndView, model, bindingResult);
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return super.view("users/login");
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm() {
        return super.redirect("/home");
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model, BindingResult bindingResult) {
        this.userRegisterValidator.validate(model, bindingResult);
        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);
            return super.view("users/user-register", modelAndView);
        }

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);


        return super.redirect("login");
    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
