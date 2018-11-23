package com.example.notesapp.Controllers;


import com.example.notesapp.Domain.NoteCategory;
import com.example.notesapp.Domain.User;
import com.example.notesapp.Repositories.NoteCategoryRepository;
import com.example.notesapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class NoteCategoryController {

    @Autowired
    private NoteCategoryRepository noteCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute(name = "noteCategory") @Valid NoteCategory noteCategory, Principal principal, @RequestParam("category") String category, Model model){



        System.out.println(category);

        noteCategory.setCategory(category);

        noteCategoryRepository.save(noteCategory);

        boolean check = true;


        model.addAttribute("saveSuccess", check);




        return "addCategory";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCat(Principal principal, Model model){
        User user = userRepository.findByUserName(principal.getName());

        model.addAttribute("ProfileImage", user);
        return "addCategory";
    }
}
