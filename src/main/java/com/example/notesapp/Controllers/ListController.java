package com.example.notesapp.Controllers;


import com.example.notesapp.Domain.Note;
import com.example.notesapp.Domain.User;
import com.example.notesapp.Repositories.NoteCategoryRepository;
import com.example.notesapp.Repositories.NoteRepository;
import com.example.notesapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ListController {

    @Autowired
    private NoteCategoryRepository noteCategoryRepository;


    @Autowired
    private UserRepository userRepo;

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(Principal principal, Model model){

        User user = userRepo.findByUserName(principal.getName());
        model.addAttribute("ProfileImage", user);


        ArrayList<Note> userNotes = noteRepository.findByUserId(user.getId());

        if(userNotes.isEmpty())
            model.addAttribute("emptyList", true);
        else{
            model.addAttribute("listNotEmpty", true);
            model.addAttribute("list", userNotes);
        }

        return "list2";
    }

//    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
//    public String addCategory(@RequestParam("content")String content, Model model){
//        NoteCategory noteCategory = new NoteCategory();
//
//        noteCategory.setCategory(content);
//        noteCategoryRepository.save(noteCategory);
//
//        model.addAttribute("categoryAdded", true);
//
//        return "edit_note";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(){
        return "login";
    }
}
