package com.example.notesapp.Controllers;

import com.example.notesapp.Domain.Note;
import com.example.notesapp.Domain.NoteCategory;
import com.example.notesapp.Domain.User;
import com.example.notesapp.Repositories.NoteCategoryRepository;
import com.example.notesapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private NoteCategoryRepository noteCategoryRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage(Principal principal, Model model){
        Note note = new Note();
        NoteCategory noteCategory = new NoteCategory();
        List<NoteCategory> noteCategoryList = noteCategoryRepository.findAll();
        User user = userRepository.findByUserName(principal.getName());

        model.addAttribute("ProfileImage", user);

        System.out.println(noteCategoryList);
        model.addAttribute("note", note);
        model.addAttribute("noteCategoryList", noteCategoryList);
        return "index";
    }


}
