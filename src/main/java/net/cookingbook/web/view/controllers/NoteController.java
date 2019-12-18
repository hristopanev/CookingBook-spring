package net.cookingbook.web.view.controllers;

import net.cookingbook.service.NoteService;
import net.cookingbook.service.UserService;
import net.cookingbook.service.models.services.NoteServiceModel;
import net.cookingbook.web.base.BaseController;
import net.cookingbook.web.view.models.binding.NoteCreateBindingModel;
import net.cookingbook.web.view.models.views.NoteViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/notes")
public class NoteController extends BaseController {

    private final NoteService noteService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public NoteController(NoteService noteService, UserService userService, ModelMapper modelMapper) {
        super(userService);
        this.noteService = noteService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView createNote(ModelAndView modelAndView, Principal principal) {
        var user = getUsername(principal);

        modelAndView.addObject("username", user.getUsername());
        return super.view("notes/create", modelAndView);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView createNoteConfirm(Principal principal, @ModelAttribute NoteCreateBindingModel model) {
        var user = getUsername(principal);
        NoteServiceModel note = this.modelMapper.map(model, NoteServiceModel.class);

        note.setUser(user);
        note.setCreateTime(new Date());
        this.noteService.createNote(note);

        return super.redirect("/note/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getAllUsersNotes(ModelAndView modelAndView, Principal principal, @ModelAttribute NoteViewModel model) {
        var user = getUsername(principal);

        var notes = this.noteService.getAllUserNotes(user.getId());

        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("notes", notes);

        return super.view("notes/all", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getNote(@PathVariable String id, Principal principal, ModelAndView modelAndView) {
        var user = getUsername(principal);

        var note = this.noteService.findById(id);

        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("note", note);
        modelAndView.addObject("noteId", id);

        return super.view("notes/details-note", modelAndView);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView deleteNote(@PathVariable String id, Principal principal, ModelAndView modelAndView) {
        var user = getUsername(principal);

        var note =this.noteService.findById(id);

        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("note", note);
        modelAndView.addObject("noteId", id);

        return super.view("notes/delete-note", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView deleteNoteConfirm(@PathVariable String id) {
        this.noteService.deleteNote(id);

        return super.redirect("/notes/all");
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView editNote(Principal principal, @PathVariable String id, ModelAndView modelAndView) {
        var note = this.noteService.findById(id);
        var user = getUsername(principal);

        modelAndView.addObject("note", note);
        modelAndView.addObject("noteId", id);
        modelAndView.addObject("username", user.getUsername());

        return super.view("notes/edit-note", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView editGroupConfig(@PathVariable String id, @ModelAttribute NoteCreateBindingModel model) {
        NoteServiceModel noteServiceModel = this.modelMapper.map(model, NoteServiceModel.class);

        this.noteService.editNote(id, noteServiceModel);

        return super.redirect("/notes/details/" + id);
    }
}
