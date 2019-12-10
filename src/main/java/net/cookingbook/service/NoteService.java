package net.cookingbook.service;

import net.cookingbook.service.models.services.NoteServiceModel;

import java.util.List;

public interface NoteService {
    NoteServiceModel createNote(NoteServiceModel noteServiceModel);

    List<NoteServiceModel> findAllNotes();

    NoteServiceModel editNote(String id, NoteServiceModel noteServiceModel);

    NoteServiceModel findById(String id);

    void deleteNote(String id);

    List<NoteServiceModel> getAllUserNotes(String id);
}
