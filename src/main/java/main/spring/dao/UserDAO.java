package main.spring.dao;

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
public class UserDAO {

    private SessionFactory sessionFactory;
    List<User> userList;

    @Autowired
    public UserDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }


    public void configureEnd()
    {
        this.sessionFactory.close();
    }

    public List<User> getUserList(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root);
            Query<User> query = session.createQuery(criteria);
            userList = query.getResultList();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return userList;
    }

//    Create user
    public void createUser(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            CriteriaBuilder builder1 = session.getCriteriaBuilder();
            CriteriaQuery<Role> q1 = builder1.createQuery(Role.class);
            Root<Role> root1 = q1.from(Role.class);

            Predicate predicateRole = builder1.equal(root1.get("name"), "ROLE_USER");
            Role role = session.createQuery(q1.where(predicateRole)).getSingleResult();
            user.setRole(role);

            session.persist(user);
            session.getTransaction().commit();
        }
        catch (NoResultException e){

        }
        finally {
             session.close();
        }
    }

//    Find by id
    public User findById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = null;
        try {
            user = session.find(User.class, id);
            session.getTransaction();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return user;
    }

//    Find by login
    public User findByLogin(String login){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> q1 = criteriaBuilder.createQuery(User.class);
            Root<User> root = q1.from(User.class);
            Predicate userByLogin = criteriaBuilder.equal(root.get("login"), login);
            user = session.createQuery(q1.where(userByLogin)).getSingleResult();
            session.getTransaction().commit();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
        return user;
    }

    //Update USER
    public void updateUser(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.update(user);
            session.getTransaction().commit();
        }
        catch (NoResultException e){

        }
        finally {
            session.close();
        }
    }


}
