package main.spring.controllers;

import main.spring.dao.*;
import main.spring.models.Answers_Sheets;
import main.spring.models.Blank;
import main.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {
    private final UserDAO userDAO;
    private final QuestionDAO questionDAO;
    private final BlankDAO blankDAO;
    private final AuthorityDAO authorityDAO;
    private final RoleDAO roleDAO;
    private final Roles_authoritiesDAO roles_authoritiesDAO;
    private final AnswersSheetsDAO answersSheetsDAO;

    @Autowired
    public QuizController(AnswersSheetsDAO answersSheetsDAO, Roles_authoritiesDAO roles_authoritiesDAO, BlankDAO blankDAO, UserDAO userDAO, QuestionDAO questionDAO, AuthorityDAO authorityDAO,  RoleDAO roleDAO)
    {
        this.answersSheetsDAO = answersSheetsDAO;
        this.roles_authoritiesDAO = roles_authoritiesDAO;
        this.userDAO = userDAO;
        this.blankDAO = blankDAO;
        this.authorityDAO = authorityDAO;
        this.roleDAO = roleDAO;
        this.questionDAO = questionDAO;
    }

    @GetMapping("/quiz/{id}")
    public String getQuiz(Principal principal, Model model, @PathVariable("id") int id){
        User us = userDAO.findByLogin(principal.getName());
        List<Answers_Sheets> list_of_answers = answersSheetsDAO.findByUserId(us.getId());
        List<Integer> blank_ids = new ArrayList<>();
        for (int i = 0; i < list_of_answers.size(); i++) {
            if (!blank_ids.contains(list_of_answers.get(i).getBlank_id())){
//                ADD ID TO ID LIST
                blank_ids.add(list_of_answers.get(i).getBlank_id());
            }
        }
        if (blank_ids.contains(id)){
            model.addAttribute("message", "You have passed this blank before! Enable to pass only once!");
        } else {
            model.addAttribute("questions", questionDAO.findByBlankId(id));
            model.addAttribute("blank", blankDAO.findById(id));
        }
        return "/quiz/quiz_page";
    }

    @PostMapping("/quiz/submit")
    public String submitQuiz(Principal principal, Model model,
                             @RequestParam("b_id") int b_id,
                             @RequestParam("q_id") List<Integer> ids,
                             @RequestParam("q_ans") List<String> answers){
        User user = userDAO.findByLogin(principal.getName());
        List<Answers_Sheets> answers_sheets = new ArrayList<>();

        for (int i = 0; i < answers.size(); i++) {
            String q_text = questionDAO.findById(ids.get(i)).getQuestion_text();
            String answer = answers.get(i);
            System.out.println("QUESTION: "+q_text+" | ANSWER: "+answer);
            Answers_Sheets answer_sheet = new Answers_Sheets(b_id, user.getId(), ids.get(i), answer);
            answers_sheets.add(answer_sheet);
        }
        answersSheetsDAO.createSheet(answers_sheets);
        model.addAttribute("message", "Blank was submited!");
        return "redirect: /quiz/passed/"+b_id;
    }

    @GetMapping("/quiz/passed/{id}")
    public String getResult(Principal principal, Model model, @PathVariable("id") int id){
        User us = userDAO.findByLogin(principal.getName());
        List<Answers_Sheets> list_of_answers = answersSheetsDAO.findByUserId(us.getId());
        List<List<String>> answer_sheets = new ArrayList<>();
        for (int i = 0; i < list_of_answers.size(); i++) {
            if (list_of_answers.get(i).getBlank_id() == id){
                List<String> answer = new ArrayList<>();
                String q_text = questionDAO.findById(list_of_answers.get(i).getQ_id()).getQuestion_text();
                String ans = list_of_answers.get(i).getQ_ans();
                String q_ans = ans;
                if (ans.equals("A")){
                    q_ans = "A. "+questionDAO.findById(list_of_answers.get(i).getQ_id()).getQuestion_variant_a();
                }
                if (ans.equals("B")){
                    q_ans = "B. "+questionDAO.findById(list_of_answers.get(i).getQ_id()).getQuestion_variant_a();
                }
                if (ans.equals("C")){
                    q_ans = "C. "+questionDAO.findById(list_of_answers.get(i).getQ_id()).getQuestion_variant_a();
                }
                answer.add(q_text);
                answer.add(q_ans);
                answer_sheets.add(answer);
            }
        }
        model.addAttribute("blank", blankDAO.findById(id));
        model.addAttribute("answersSheet", answer_sheets);

        return "/quiz/quiz_results";
    }
}
