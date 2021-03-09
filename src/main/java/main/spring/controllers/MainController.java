package main.spring.controllers;

import main.spring.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private final UserDAO userDAO;
    private final QuestionDAO questionDAO;
    private final BlankDAO blankDAO;
    private final AuthorityDAO authorityDAO;
    private final RoleDAO roleDAO;
    private final Roles_authoritiesDAO roles_authoritiesDAO;

    @Autowired
    public MainController(Roles_authoritiesDAO roles_authoritiesDAO, BlankDAO blankDAO, UserDAO userDAO, QuestionDAO questionDAO, AuthorityDAO authorityDAO,  RoleDAO roleDAO)
    {
        this.roles_authoritiesDAO = roles_authoritiesDAO;
        this.userDAO = userDAO;
        this.blankDAO = blankDAO;
        this.authorityDAO = authorityDAO;
        this.roleDAO = roleDAO;
        this.questionDAO = questionDAO;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("blanks", blankDAO.getAllBlanks());
        return "/main/main";
    }



}
