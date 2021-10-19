package com.codefellowship.codefellowship.controllers;

import com.codefellowship.codefellowship.models.ApplicationUser;
import com.codefellowship.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }


    @PostMapping("/signup")
    public RedirectView signup(String username,
                               String password,
                               String firstName,
                               String lastName,
                               String dateOfBirth,
                               String bio){

        ApplicationUser newUser = new ApplicationUser(username,passwordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        newUser = applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser,null,new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/login");
    }

    @GetMapping("/users/{id}")
    public String getProfile(Model model, @PathVariable long id){
        try {
            ApplicationUser user = applicationUserRepository.findById(id).get();
            model.addAttribute("user", user);
            return "profile";
        }
        catch(Exception e){
            return "profile";
        }

    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

}
