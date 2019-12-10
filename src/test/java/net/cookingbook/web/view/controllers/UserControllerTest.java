package net.cookingbook.web.view.controllers;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.web.base.ViewTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import  org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class UserControllerTest  extends ViewTestBase {

    @MockBean
    UserRepository mockUserRepository;

    @Autowired
    UserController controller;

    @Test
    void getDetails_whenNoUserWithId_shouldReturnErrorViewWith404() throws Exception {
        String userId = "1";

        mockMvc.perform(get("/users/details/" + userId))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"));
    }

    @Test
    void getDetails_whenNoUserWithId_shouldReturnUserDetailsViewWith200() throws Exception {
        String userId = "1";
        User user = new User();
        user.setId("1");

        Mockito.when(mockUserRepository.findUserById(userId))
                .thenReturn(user);

        mockMvc.perform(get("/users/details/" + userId))
                .andExpect(status().isOk())
                .andExpect(view().name(UserController.USER_DETAILS_VIEW));
    }

}