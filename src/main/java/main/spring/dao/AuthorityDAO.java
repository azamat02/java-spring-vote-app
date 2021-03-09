package main.spring.dao;

import main.spring.models.*;
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
public class AuthorityDAO {

    private SessionFactory sessionFactory;
    List<Authority> authorityList;

    @Autowired
    public AuthorityDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void configureEnd()
    {
        this.sessionFactory.close();
    }

    public List<Authority> getAllAuthorities(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Authority> criteria = builder.createQuery(Authority.class);
            Root<Authority> root = criteria.from(Authority.class);
            criteria.select(root);
            Query<Authority> query = session.createQuery(criteria);
            authorityList = query.getResultList();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return authorityList;
    }

    //    Find authority by id
    public Authority findById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Authority authority = null;
        try {
            authority = session.find(Authority.class, id);
            session.getTransaction();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return authority;
    }
}
