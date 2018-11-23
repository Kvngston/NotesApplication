package com.example.notesapp.Repositories;

import com.example.notesapp.Domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface NoteRepository extends JpaRepository<Note, Long> {

    ArrayList<Note> findByUserId(Integer id);
    Note findByNoteIdAndUserId(Long id, Integer userId);
}
