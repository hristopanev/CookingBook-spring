package net.cookingbook.web.api.controllers;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.models.Group;
import net.cookingbook.data.models.User;
import net.cookingbook.data.repository.UserRepository;
import net.cookingbook.web.api.models.UsersGroupsResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersApiControllerTest extends TestBase {

    @MockBean
    UserRepository userRepository;

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void test() {
        var result = restTemplate.getForObject("http://localhost:" + port + "/api/users", String.class);
    }


}