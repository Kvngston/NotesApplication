package com.example.notesapp.Controllers;

import com.example.notesapp.Domain.User;
import com.example.notesapp.Repositories.RoleRepository;
import com.example.notesapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static String uploadDirectory = "src\\main\\resources\\static\\images\\profileImages";

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/editProf", method = RequestMethod.GET)
    public String editProf(Principal principal, Model model) {

        User user = userRepo.findByUserName(principal.getName());

        model.addAttribute("ProfileImage", user);

        return "editProfile";
    }


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    private String addUser(@ModelAttribute(name = "user") @Valid User user, BindingResult errors, @RequestParam("profileImg") MultipartFile files, @RequestParam("cpassword") String cPassword, @RequestParam("dateOfBirth") String dateOfBirth, Model model) {
        System.out.println(user);
        System.out.println(errors);
        if (errors.hasErrors()) {
            if (!(user.getPassword().equals(cPassword))) {
                System.out.println("Password mis match");
                model.addAttribute("passwordMismatch", true);
            }
            if (files.isEmpty()) {
                System.out.println("Profile Image is empty");
                model.addAttribute("empty", true);
            }
            model.addAttribute("error", true);
            return "register";
        }


        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setPassword(user.getPassword());


        System.out.println(files.getOriginalFilename());
        user.setDateOfBirth(dateOfBirth);

        user.setProfileImage(files.getOriginalFilename());

        user.setRole(roleRepository.getOne(1));


        Path fileNameAndPath = Paths.get(uploadDirectory, files.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, files.getBytes());
        } catch (IOException ex) {
            System.err.println("Error " + ex.getMessage());
        }
        System.out.println(user);
        userRepo.save(user);
        return "login";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String editProfile(Principal principal, Model model) {

        User user = userRepo.findByUserName(principal.getName());

        String message  = "Edit Profile for " + user.getUserName();
        model.addAttribute("user", user);
        model.addAttribute("message", message);

        return "editProfile";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult,
                                Principal principal,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("userName") String userName,
                                @RequestParam("email") String email,
                                @RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("dateOfBirth") String dateOfBirth,
                                @RequestParam("prof-image") MultipartFile files,
                                @RequestParam("Password") String Password,
                                @RequestParam("cpassword") String cpassword,
                                Model model
    ) {
        System.out.println(files);
        if (bindingResult.hasErrors()) {
            if (!(Password.equals(cpassword))) {
                System.out.println("Password mis match");
                model.addAttribute("passwordMismatch", true);
            }
            if (files.isEmpty()) {
                System.out.println("Profile Image is empty");
                model.addAttribute("empty", true);
            }
        }
            model.addAttribute("error", true);


            User currentUser = userRepo.findByUserName(principal.getName());

            user.setId(currentUser.getId());
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserName(userName);
            user.setEmail(email);
            user.setDateOfBirth(dateOfBirth);
            user.setRole(roleRepository.getOne(1));
            user.setPhoneNumber(phoneNumber);
            user.setProfileImage(files.getOriginalFilename());

            Path fileNameAndPath = Paths.get(uploadDirectory, files.getOriginalFilename());
            Path fileName = Paths.get(currentUser.getProfileImage());
            try {

                Files.write(fileNameAndPath, files.getBytes());
                Files.deleteIfExists(fileName);
            } catch (IOException ex) {
                System.err.println("Error " + ex.getMessage());
            }

            user.setPassword(bCryptPasswordEncoder.encode(Password));



            userRepo.save(user);
            model.addAttribute("ProfileUpdated", true);


            return "redirect:/login";
    }
}
