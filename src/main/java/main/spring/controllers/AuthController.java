package main.spring.controllers;

import main.spring.dao.UserDAO;
import main.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserDAO userDAO;

    PasswordEncoder passwordEncoder;
    @Autowired
    public void PasswordEncoder(PasswordEncoder passwordEncoder)
    { this.passwordEncoder = passwordEncoder;}

    @Autowired
    public AuthController(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @GetMapping("/signin")
    public String getLoginPage(){
        return "/auth/sign_in";
    }

    @GetMapping("/signup")
    public String getRegPage(Model model){
        model.addAttribute("user", new User());
        return "/auth/sign_up";
    }

    //Change password
    @PostMapping("/changepass")
    public String changePass(Principal principal, Model model, @RequestParam("password") String password, @RequestParam("password2") String password2){
        User us = userDAO.findByLogin(principal.getName());
        password = passwordEncoder.encode(password);
        us.setPassword(password);
        userDAO.updateUser(us);
        model.addAttribute("message", "Password successfully changed!");
        return "/profile/profile";
    }

    //Change DETAILS
    @PostMapping("/changedetails")
    public String changeDetails(Principal principal, Model model,
                                @RequestParam("first_name") String fname,
                                @RequestParam("last_name") String lname,
                                @RequestParam("age") int age,
                                @RequestParam("interests") String ints){
        User us = userDAO.findByLogin(principal.getName());
        us.setFirst_name(fname);
        us.setLast_name(lname);
        us.setAge(age);
        us.setInterests(ints);
        userDAO.updateUser(us);
        model.addAttribute("message", "Details successfully changed!");
        return "/profile/profile";
    }


}
