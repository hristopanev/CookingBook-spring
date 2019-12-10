package net.cookingbook.service.imlementations;

import net.cookingbook.data.models.Note;
import net.cookingbook.data.repository.NoteRepository;
import net.cookingbook.service.NoteService;
import net.cookingbook.service.models.services.NoteServiceModel;
import net.cookingbook.service.models.services.PostServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NoteServiceModel createNote(NoteServiceModel noteServiceModel) {
        Note note = this.modelMapper.map(noteServiceModel, Note.class);

        return this.modelMapper.map(this.noteRepository.saveAndFlush(note), NoteServiceModel.class);
    }

    @Override
    public List<NoteServiceModel> findAllNotes() {
        return this.noteRepository.findAll()
                .stream()
                .map(n -> this.modelMapper.map(n, NoteServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public NoteServiceModel editNote(String id, NoteServiceModel noteServiceModel) {
        Note note = this.noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        try {
            note.setTitle(noteServiceModel.getTitle());
            note.setDescription(noteServiceModel.getDescription());
        }catch (Exception e) {
            return null;
        }
        return this.modelMapper.map(this.noteRepository.saveAndFlush(note), NoteServiceModel.class);
    }

    @Override
    public NoteServiceModel findById(String id) {
        Note note = this.noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return this.modelMapper.map(note, NoteServiceModel.class);
    }

    @Override
    public void deleteNote(String id) {
        Note note = this.noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        this.noteRepository.delete(note);
    }

    @Override
    public List<NoteServiceModel> getAllUserNotes(String id) {

        //TODO IT's WORK?
        return this.noteRepository.findAllByUser_IdContains(id)
                .stream()
                .map(n -> this.modelMapper.map(n, NoteServiceModel.class))
                .collect(Collectors.toList());
    }
}
