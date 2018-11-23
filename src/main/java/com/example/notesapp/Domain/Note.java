package com.example.notesapp.Domain;

import com.example.notesapp.Controllers.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Note extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @NotEmpty(message = "Enter a Title for the Note")
    @Size(min = 2)
    private String title;

    @NotEmpty(message = "Enter the content of the note")
    @Column(length=8192)
    private String content;

    @ManyToOne
    @JoinColumn(name = "note_category_id")
    private NoteCategory noteCategory;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Note() {
    }

    public Note(String title, String content, NoteCategory noteCategory, User user) {
        this.title = title;
        this.content = content;
        this.noteCategory = noteCategory;
        this.user = user;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoteCategory getNoteCategory() {
        return noteCategory;
    }

    public void setNoteCategory(NoteCategory noteCategory) {
        this.noteCategory = noteCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", noteCategory=" + noteCategory +
                '}';
    }
}
