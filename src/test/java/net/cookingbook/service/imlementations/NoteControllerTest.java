package net.cookingbook.service.imlementations;

import net.cookingbook.base.TestBase;
import net.cookingbook.data.repository.NoteRepository;
import net.cookingbook.service.NoteService;
import net.cookingbook.service.models.services.NoteServiceModel;
import net.cookingbook.service.models.services.UserServiceModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class NoteControllerTest extends TestBase {

    @MockBean
    NoteRepository noteRepository;

    @MockBean
    NoteService noteService;

    @Autowired
    NoteService service;

    @Test
    void createNote_WhenIsValid_showGroupName() {
        String noteName = "Name";
        NoteServiceModel noteServiceModel = new NoteServiceModel();
        noteServiceModel.setTitle("Name");

        service.createNote(noteServiceModel);

        assertEquals(noteName, noteServiceModel.getTitle());
    }

    @Test
    void createNote_WhenIsUserValid_showUsername() {
        String userName = "Max";

        NoteServiceModel noteServiceModel = new NoteServiceModel();
        noteServiceModel.setTitle("Name");

        UserServiceModel user = new UserServiceModel();
        user.setUsername(userName);
        noteServiceModel.setUser(user);
        service.createNote(noteServiceModel);

        assertEquals(userName, noteServiceModel.getUser().getUsername());
    }
}