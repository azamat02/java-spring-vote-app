package main.spring.dao;

import main.spring.models.Question;
import main.spring.models.Role;
import main.spring.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@Scope(scopeName = "prototype")
public class QuestionDAO {

    private SessionFactory sessionFactory;
    List<Question> questionList;

    @Autowired
    public QuestionDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void configureEnd()
    {
        this.sessionFactory.close();
    }

    public List<Question> getAllQuestions(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Question> criteria = builder.createQuery(Question.class);
            Root<Question> root = criteria.from(Question.class);
            criteria.select(root);
            Query<Question> query = session.createQuery(criteria);
            questionList = query.getResultList();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return questionList;
    }

    //    Create question
    public void createQuestion(final Question question){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                try {
                    session.persist(question);
                    session.getTransaction().commit();
                }
                catch (NoResultException e){

                }
                finally {
                    session.close();
                }
            }
        }).start();
    }

    //    Find question by id
    public Question findById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Question question = null;
        try {
            question = session.find(Question.class, id);
            session.getTransaction();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return question;
    }

    //    Find question by blank id
    public List<Question> findByBlankId(int blank_id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Question> questions = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Question> q1 = criteriaBuilder.createQuery(Question.class);
            Root<Question> root = q1.from(Question.class);
            Predicate questionByBlankId = criteriaBuilder.equal(root.get("blank_id"), blank_id);
            questions = session.createQuery(q1.where(questionByBlankId)).getResultList();
            session.getTransaction().commit();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return questions;
    }

    //Update question
    public void updateQuestion(final Question question){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                try {
                    session.update(question);
                    session.getTransaction().commit();
                }
                catch (NoResultException e){

                }
                finally {
                    session.close();
                }
            }
        }).start();
    }

//    Delete question
    public void deleteQuestion(final int id)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session;
                Question question;

                session = sessionFactory.openSession();
                session.beginTransaction();

                try {
                    question = session.find(Question.class, id);
                    session.delete(question);
                    session.getTransaction().commit();
                } catch (NoResultException e){

                }
                finally {
                    session.close();
                }
            }
        }).start();
    }

}
