package main.spring.dao;

import main.spring.models.Blank;
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
public class BlankDAO {

    private SessionFactory sessionFactory;
    List<Blank> blankList;

    @Autowired
    public BlankDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void configureEnd()
    {
        this.sessionFactory.close();
    }

    public List<Blank> getAllBlanks(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Blank> criteria = builder.createQuery(Blank.class);
            Root<Blank> root = criteria.from(Blank.class);
            criteria.select(root);
            Query<Blank> query = session.createQuery(criteria);
            blankList = query.getResultList();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return blankList;
    }

    //    Create blank
    public void createBlank(final Blank blank){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                try {
                    session.persist(blank);
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

    //    Find blank by id
    public Blank findById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Blank blank = null;
        try {
            blank = session.find(Blank.class, id);
            session.getTransaction();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return blank;
    }
}
