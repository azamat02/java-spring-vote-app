package main.spring.controllers;

import main.spring.dao.AnswersSheetsDAO;
import main.spring.dao.BlankDAO;
import main.spring.dao.UserDAO;
import main.spring.models.Answers_Sheets;
import main.spring.models.Blank;
import main.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserDAO userDAO;
    private final BlankDAO blankDAO;
    private final AnswersSheetsDAO answersSheetsDAO;

    PasswordEncoder passwordEncoder;
    @Autowired
    public void PasswordEncoder(PasswordEncoder passwordEncoder)
    { this.passwordEncoder = passwordEncoder;}

    @Autowired
    public ProfileController(UserDAO userDAO, BlankDAO blankDAO, AnswersSheetsDAO answersSheetsDAO)
    {
        this.blankDAO = blankDAO;
        this.answersSheetsDAO = answersSheetsDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("")
    public String profilePage(Model model, Principal principal){
        User us = userDAO.findByLogin(principal.getName());
            List<Answers_Sheets> list_of_answers = answersSheetsDAO.findByUserId(us.getId());
            List<Blank> blanks = new ArrayList<>();
            List<Integer> blank_ids = new ArrayList<>();
            for (int i = 0; i < list_of_answers.size(); i++) {
                if (!blank_ids.contains(list_of_answers.get(i).getBlank_id())){
//                ADD ID TO ID LIST
                    blank_ids.add(list_of_answers.get(i).getBlank_id());
//                ADD FINDED BLANK TO LIST
                    blanks.add(blankDAO.findById(list_of_answers.get(i).getBlank_id()));
                }
        }
        model.addAttribute("blanks", blanks);
        model.addAttribute("user", us);
        return "/profile/profile";
    }

}
