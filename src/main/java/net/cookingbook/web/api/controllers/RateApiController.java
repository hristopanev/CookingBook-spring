package net.cookingbook.web.api.controllers;

import net.cookingbook.service.RateService;
import net.cookingbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@RestController
public class RateApiController {
    private final RateService rateService;
    private final UserService userService;

    @Autowired
    public RateApiController(RateService rateService, UserService userService) {
        this.rateService = rateService;
        this.userService = userService;
    }

    @PostMapping("/api/rate/{id}")
    public void rateRecipe(@PathVariable String id, HttpSession session, HttpServletResponse response, Principal principal) throws IOException {
        var user =this.userService.findUserByUserName(principal.getName());
        if (this.rateService.userHasRated(id, user.getId())) {
            var rate = this.rateService.rate(id, user.getId());
        }

        response.sendRedirect("/details/{id}");
    }
}
