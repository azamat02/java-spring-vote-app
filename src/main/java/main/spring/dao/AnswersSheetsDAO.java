package main.spring.dao;

import main.spring.models.Answers_Sheets;
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
public class AnswersSheetsDAO {

    private SessionFactory sessionFactory;
    List<Answers_Sheets> answersSheetsList;

    @Autowired
    public AnswersSheetsDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void configureEnd()
    {
        this.sessionFactory.close();
    }

    public List<Answers_Sheets> getAllAnswerSheets(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Answers_Sheets> criteria = builder.createQuery(Answers_Sheets.class);
            Root<Answers_Sheets> root = criteria.from(Answers_Sheets.class);
            criteria.select(root);
            Query<Answers_Sheets> query = session.createQuery(criteria);
            answersSheetsList = query.getResultList();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return answersSheetsList;
    }

    //    Create sheet
    public void createSheet(final List<Answers_Sheets> answersSheets){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                try {
                    for (int i = 0; i < answersSheets.size(); i++) {
                        session.persist(answersSheets.get(i));
                    }
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
    public Answers_Sheets findById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Answers_Sheets answersSheets = null;
        try {
            answersSheets = session.find(Answers_Sheets.class, id);
            session.getTransaction();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return answersSheets;
    }

    //    Find answers sheet by blank id
    public List<Answers_Sheets> findByBlankId(int blank_id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Answers_Sheets> answersSheets = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Answers_Sheets> q1 = criteriaBuilder.createQuery(Answers_Sheets.class);
            Root<Answers_Sheets> root = q1.from(Answers_Sheets.class);
            Predicate questionByBlankId = criteriaBuilder.equal(root.get("blank_id"), blank_id);
            answersSheets = session.createQuery(q1.where(questionByBlankId)).getResultList();
            session.getTransaction().commit();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return answersSheets;
    }

    //    Find answers sheet by USER id
    public List<Answers_Sheets> findByUserId(int user_id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Answers_Sheets> answersSheets = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Answers_Sheets> q1 = criteriaBuilder.createQuery(Answers_Sheets.class);
            Root<Answers_Sheets> root = q1.from(Answers_Sheets.class);
            Predicate questionByBlankId = criteriaBuilder.equal(root.get("user_id"), user_id);
            answersSheets = session.createQuery(q1.where(questionByBlankId)).getResultList();
            session.getTransaction().commit();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return answersSheets;
    }


}
