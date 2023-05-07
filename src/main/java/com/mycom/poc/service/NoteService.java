package com.mycom.poc.service;

import com.mycom.poc.model.Note;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NoteService {

  private final Map<String, Note> memory = new HashMap<>();

  public List<Note> getAll() {
    log.info("Retrieving all notes");
    return new ArrayList<>(memory.values());
  }

  public void saveNote(Note note) {
    log.info("Note : {}", note);
    memory.put(note.getId(), note);
  }

  public Optional<Note> findById(String noteId) {
    log.info("Find note by id:{}", noteId);
    return Optional.ofNullable(memory.get(noteId));
  }

}
