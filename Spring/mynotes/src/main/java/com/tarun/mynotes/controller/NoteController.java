package com.tarun.mynotes.controller;


import com.tarun.mynotes.model.Note;
import com.tarun.mynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController
{
    @Autowired
    NoteRepository noteRepository;


    @GetMapping("/notes")
    public List<Note> findAll()
    {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note)
    {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value="id") Long noteId)
    {
        return noteRepository.findById(noteId).orElse(null);
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value="id") Long noteId,@Valid @RequestBody Note noteBody)
    {
        Note note = noteRepository.findById(noteId).orElseGet(Note::new);
        note.setTitle(noteBody.getTitle());
        note.setContent(noteBody.getContent());

        return noteRepository.save(note);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId).orElse(new Note());
        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}
