package com.example.notesapp.Domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class NoteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String category;


    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "noteCategory",
        cascade = CascadeType.ALL
    )
    private Set<Note> notes;


    public NoteCategory() {
    }

    public NoteCategory(String category) {
        this.category = category;
    }

    public NoteCategory(String category, Set<Note> notes) {
        this.category = category;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "NoteCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
