package com.example.notesapp;

import com.example.notesapp.Domain.NoteCategory;
import com.example.notesapp.Domain.Role;
import com.example.notesapp.Repositories.NoteCategoryRepository;
import com.example.notesapp.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NotesappApplication implements CommandLineRunner {

    @Autowired
    private NoteCategoryRepository noteCategoryRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(NotesappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        NoteCategory noteCategory = new NoteCategory("Home");
//        NoteCategory noteCategory2 = new NoteCategory("Work");
//        NoteCategory noteCategory3 = new NoteCategory("Office");
//        NoteCategory noteCategory4 = new NoteCategory("Personal");
//        NoteCategory noteCategory5 = new NoteCategory("School");
//
//
//        noteCategoryRepository.save(noteCategory);
//        noteCategoryRepository.save(noteCategory2);
//        noteCategoryRepository.save(noteCategory3);
//        noteCategoryRepository.save(noteCategory4);
//        noteCategoryRepository.save(noteCategory5);
//
//        Role role = new Role("user");
//
//        roleRepository.save(role);
    }
}
