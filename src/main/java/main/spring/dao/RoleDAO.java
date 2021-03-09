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
public class RoleDAO {

    private SessionFactory sessionFactory;
    List<Role> roleList;

    @Autowired
    public RoleDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void configureEnd()
    {
        this.sessionFactory.close();
    }

    public List<Role> getAllRoles(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
            Root<Role> root = criteria.from(Role.class);
            criteria.select(root);
            Query<Role> query = session.createQuery(criteria);
            roleList = query.getResultList();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return roleList;
    }

    //    Find role by id
    public Role findById(long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Role role = null;
        try {
            role = session.find(Role.class, id);
            session.getTransaction();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return role;
    }

    //    Create role
    public void createRole(Role role){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(role);
            session.getTransaction().commit();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
    }

    //    Delete role
    public void deleteRole(final long id)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session;
                Role role;

                session = sessionFactory.openSession();
                session.beginTransaction();

                try {
                    role = session.find(Role.class, id);
                    session.delete(role);
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