package com.example.notesapp.Repositories;

import com.example.notesapp.Domain.NoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteCategoryRepository extends JpaRepository<NoteCategory, Integer> {

}
