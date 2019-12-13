package net.cookingbook.web.view.controllers;

import net.cookingbook.service.MessageService;
import net.cookingbook.service.UserService;
import net.cookingbook.service.models.services.MessageServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;
import net.cookingbook.validation.message.MessageSendValidator;
import net.cookingbook.web.base.BaseController;
import net.cookingbook.web.view.models.binding.MessageSendBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/messages")
public class MessageController extends BaseController {

    private final MessageService messageService;
    private final ModelMapper modelMapper;
    private final MessageSendValidator messageSendValidator;
    private final UserService userService;

    @Autowired
    public MessageController(UserService userService, MessageService messageService, ModelMapper modelMapper, MessageSendValidator messageSendValidator, UserService userService1) {
        super(userService);
        this.messageService = messageService;
        this.modelMapper = modelMapper;
        this.messageSendValidator = messageSendValidator;
        this.userService = userService1;
    }

    @GetMapping("/send/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView sendMessage(@PathVariable String id, ModelAndView modelAndView, Principal principal, @ModelAttribute(name = "model") MessageSendBindingModel model) {

        var user = getUsername(principal);

        modelAndView.addObject("model", model);
        modelAndView.addObject("userID", id);
        modelAndView.addObject("username", user.getUsername());

        return super.view("messages/send" , modelAndView);
    }

    @PostMapping("/send/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView sendMessageConfirm(@PathVariable String id, Principal principal, ModelAndView modelAndView, @ModelAttribute(name = "model") MessageServiceModel model, BindingResult bindingResult) {

        this.messageSendValidator.validate(model, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);

            return super.view("/message/send", modelAndView);
        }

        UserServiceModel loggedUser = getUsername(principal);
        UserServiceModel user = this.userService.findById(id);

        MessageServiceModel messageServiceModel = this.modelMapper.map(model, MessageServiceModel.class);
        messageServiceModel.setSender(loggedUser);
        messageServiceModel.setUserServiceModel(user);
        this.messageService.sendMessage(messageServiceModel);

        return super.redirect("/users/details/" + id);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView allMessage(Principal principal, @ModelAttribute MessageServiceModel model, ModelAndView modelAndView) {
        var user = getUsername(principal);
        var messages = this.messageService.findAllByUser(user.getId());

        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("messages", messages);

        return super.view("messages/all" , modelAndView);
    }

    @GetMapping("/message/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView message(@PathVariable String id, Principal principal, @ModelAttribute MessageServiceModel model, ModelAndView modelAndView) {
        var user = getUsername(principal);
        var message = this.messageService.findMessageById(id);

        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("message", message);

        return super.view("messages/message" , modelAndView);
    }
}
