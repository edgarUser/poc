package com.mycom.poc.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
class NoteServiceTest {

  @MockBean
  private NoteService noteService;

  @Test
  void getAllNotes() {
    when(noteService.getAll()).thenReturn(new ArrayList<>());
    assertAll(() -> assertNotNull(noteService.getAll()));
  }

}
