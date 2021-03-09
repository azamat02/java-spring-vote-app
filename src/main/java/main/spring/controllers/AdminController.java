package main.spring.controllers;

import main.spring.dao.*;
import main.spring.models.Blank;
import main.spring.models.Question;
import main.spring.models.Role;
import main.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    private final UserDAO userDAO;
    private final QuestionDAO questionDAO;
    private final BlankDAO blankDAO;
    private final AuthorityDAO authorityDAO;
    private final RoleDAO roleDAO;
    private final Roles_authoritiesDAO roles_authoritiesDAO;

    @Autowired
    public AdminController(Roles_authoritiesDAO roles_authoritiesDAO, BlankDAO blankDAO, UserDAO userDAO, QuestionDAO questionDAO, AuthorityDAO authorityDAO,  RoleDAO roleDAO)
    {
        this.roles_authoritiesDAO = roles_authoritiesDAO;
        this.userDAO = userDAO;
        this.blankDAO = blankDAO;
        this.authorityDAO = authorityDAO;
        this.roleDAO = roleDAO;
        this.questionDAO = questionDAO;
    }

    @GetMapping("/admin")
    public String adminPanelPage(Principal principal, Model model, HttpServletRequest request){
        request.getSession().setAttribute("admin_name", principal.getName());
        model.addAttribute("users", userDAO.getUserList());
        model.addAttribute("blanks", blankDAO.getAllBlanks());
        model.addAttribute("questions", questionDAO.getAllQuestions());
        model.addAttribute("roles", roleDAO.getAllRoles());
        model.addAttribute("authorities", authorityDAO.getAllAuthorities());
        return "/main/admin_panel";
    }

    @PostMapping("/admin/add_question")
    public String addQuestion(Model model,
                              @RequestParam("q_text") String q_text,
                              @RequestParam("q_option_a") String q_option_a,
                              @RequestParam("q_option_b") String q_option_b,
                              @RequestParam("q_option_c") String q_option_c,
                              @RequestParam("blank_id") int blank_id){
        questionDAO.createQuestion(new Question(q_text, q_option_a, q_option_b, q_option_c, blank_id));
        model.addAttribute("message", "New question was added!");
        return "/main/admin_panel";
    }

    @PostMapping("/admin/add_blank")
    public String addBlank(Model model,
                              @RequestParam("b_name") String b_name,
                              @RequestParam("b_topic") String b_topic){
        blankDAO.createBlank(new Blank(b_name, b_topic));
        model.addAttribute("message", "New blank was created!");
        return "/main/admin_panel";
    }

    @PostMapping("/admin/update_question")
    public String updateQuestion(Model model,
                                 @RequestParam("q_text") String q_text,
                                 @RequestParam("q_id") int q_id,
                                 @RequestParam("q_option_a") String q_option_a,
                                 @RequestParam("q_option_b") String q_option_b,
                                 @RequestParam("q_option_c") String q_option_c,
                                 @RequestParam("blank_id") int blank_id){
        Question question = questionDAO.findById(q_id);
        question.setQuestion_text(q_text);
        question.setQuestion_variant_a(q_option_a);
        question.setQuestion_variant_b(q_option_b);
        question.setQuestion_variant_c(q_option_c);
        question.setBlank_id(blank_id);
        questionDAO.updateQuestion(question);
        model.addAttribute("message", "Question was updated!");
        return "/main/admin_panel";
    }

    @PostMapping("/admin/delete_question")
    public String deleteQuestion(Model model, @RequestParam("q_id") int q_id){
        questionDAO.deleteQuestion(q_id);
        model.addAttribute("message", "Question was deleted!");
        return "/main/admin_panel";
    }

//---------------
    @PostMapping("/admin/add_authority")
    public String addAuthority(Model model,
                              @RequestParam("r_id") int r_id,
                              @RequestParam("a_id") int a_id){
        roles_authoritiesDAO.addRoleAuthority(r_id, a_id);
        model.addAttribute("message", "Authority was assigned successfully!");
        return "/main/admin_panel";
    }

    @PostMapping("/admin/delete_authority")
    public String deleteAuthority(Model model,
                                  @RequestParam("r_id") int r_id,
                                  @RequestParam("a_id") int a_id){

        boolean status = roles_authoritiesDAO.deleteAuthority(r_id, a_id);
        String message = "";
        if (status == true) {
            message = "Authority was successfully deleted!";
        }
        else {
            message = "Selected authority not exist for this role!";
        }
        model.addAttribute("message", message);
        return "/main/admin_panel";
    }
//---------------
    @PostMapping("/admin/add_role")
    public String addRole(Model model,
                                  @RequestParam("role_name") String role_name){

        roleDAO.createRole(new Role(role_name));
        model.addAttribute("message", "Role created!");
        return "/main/admin_panel";
    }

    @PostMapping("/admin/delete_role")
    public String deleteRole(Model model,
                            @RequestParam("r_id") int r_id){
        roles_authoritiesDAO.deleteAllAuthority(r_id);
        roleDAO.deleteRole(r_id);
        model.addAttribute("message", "Role was deleted successfully!");
        return "/main/admin_panel";
    }

    @PostMapping("/admin/edit_role")
    public String editRole(Model model,
                           @RequestParam("u_id") int u_id,
                           @RequestParam("r_id") int r_id){
        User user = userDAO.findById(u_id);
        Role role = roleDAO.findById(r_id);
        user.setRole(role);
        userDAO.updateUser(user);
        model.addAttribute("message", "Role was edited successfully!");
        return "/main/admin_panel";
    }

}
