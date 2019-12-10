package net.cookingbook.web.view.controllers;

import net.cookingbook.service.RateService;
import net.cookingbook.service.UserService;
import net.cookingbook.web.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/rates")
public class RateController extends BaseController {

    private final RateService rateService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RateController(RateService rateService, UserService userService, ModelMapper modelMapper) {
        super(userService);
        this.rateService = rateService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/vote/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getRate(@PathVariable String id, Principal principal) {
        var user = getUsername(principal);
        boolean isVoted = this.rateService.userHasRated(id, user.getId());
        if (!isVoted) {
            var rate = this.rateService.rate(id, user.getId());
        }

        return super.redirect("/posts/details/" + id);
    }
}
