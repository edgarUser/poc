package com.mycom.poc.controller;

import com.mycom.poc.model.Note;
import com.mycom.poc.service.NoteService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class NoteController {

  private NoteService noteService;

  @GetMapping(path = "/note", produces = "application/json")
  public ResponseEntity<List<Note>> getAllNotes() {
    return ResponseEntity.ok(noteService.getAll());
  }

  @PutMapping(path = "/note", produces = "application/json")
  public ResponseEntity<Void> createNote(@RequestBody Note note) {
    noteService.saveNote(note);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(path = "/note/{nodeId}", produces = "application/json")
  public ResponseEntity<Note> getNote(@PathVariable(name = "nodeId") String nodeId) {
    return noteService.findById(nodeId).map(note -> new ResponseEntity<>(note, HttpStatus.OK))
        .orElseThrow(() -> new RuntimeException("Note not found"));
  }
}
