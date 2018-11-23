package com.example.notesapp.Controllers;


import com.example.notesapp.Domain.Note;
import com.example.notesapp.Domain.NoteCategory;
import com.example.notesapp.Domain.User;
import com.example.notesapp.Repositories.NoteCategoryRepository;
import com.example.notesapp.Repositories.NoteRepository;
import com.example.notesapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/noteApp", method = RequestMethod.GET)
public class NoteController {

    @Autowired
    private NoteCategoryRepository noteCategoryRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/addNote" ,method = RequestMethod.POST)
    public String addNote(@ModelAttribute(name = "note")@Valid Note note, BindingResult bindingResult, Principal principal, @RequestParam int category, Model model){
        User user = userRepository.findByUserName(principal.getName());

        note.setUser(user);
        note.setNoteCategory(noteCategoryRepository.getOne(category));

        boolean check = false;

        //assert
        System.out.println("New Item : " + note);

        //check if the form has errors
        if(bindingResult.hasErrors())
            return "list";

        //submitting the form to the db
        noteRepository.save(note);
        check = true;

        if(check){
            model.addAttribute("check", check);
            model.addAttribute("list", noteRepository.findAll());
            model.addAttribute("ProfileImage", user);
        }




        return "redirect:/list";
    }

    @RequestMapping(value = "/delete{noteId}", method = RequestMethod.POST)
    public String delete(@PathVariable("noteId") Long noteId, Principal principal, Model model){
        User user = userRepository.findByUserName(principal.getName());

        noteRepository.deleteById(noteId);

        model.addAttribute("list", noteRepository.findAll());

        return "redirect:/list";
    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addCategory(@RequestParam("content")String content, Model model){
//        NoteCategory noteCategory = new NoteCategory();
//
//        noteCategory.setCategory(content);
//        noteCategoryRepository.save(noteCategory);
//        model.addAttribute("categoryAdded", true);
//
//        return "index";
//    }

    @RequestMapping(value = "/edit{noteId}", method = RequestMethod.GET)
    public String edit(@PathVariable(name = "noteId") Long noteId, Principal principal, Model model){

        User user = userRepository.findByUserName(principal.getName());

        Note userNote = noteRepository.findByNoteIdAndUserId(noteId,user.getId());

        noteId = userNote.getNoteId();
        System.out.println("Note id in the page is " +noteId);


        String message  = "Edit Note " + userNote.getNoteId();

        if(!(userNote.toString() == null)) {
            model.addAttribute("note", userNote);
        }else{
            System.out.println("Something is wrong");
        }

       List<NoteCategory> noteCategoryList = noteCategoryRepository.findAll();
        model.addAttribute("noteCategoryList", noteCategoryList);
        model.addAttribute("check", false);
        model.addAttribute("message", message);
        model.addAttribute("user", user);

        return "edit_note";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String editNote(@ModelAttribute("note") @Valid Note note,
                           BindingResult bindingResult,
                           Principal principal,
                           @RequestParam("NoteId") Long NoteId,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("category") int category,
                           Model model){
        User user = userRepository.findByUserName(principal.getName());

        System.out.println("NoteId inside the upload page" + NoteId);

        if(category == 0) {
            model.addAttribute("IncorrectCategory", true);
            return "edit_note";
        }

        System.out.println("noteId is" + note.getNoteId());
        //note.setNoteId(note.getNoteId());
        note.setContent(content);
        note.setTitle(title);
        note.setUser(user);
        note.setNoteCategory(noteCategoryRepository.getOne(category));
        //note.setUser(user);

        noteRepository.save(note);
        return "redirect:/list";
    }
}
