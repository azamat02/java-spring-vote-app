package main.spring.dao;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
public class Roles_authoritiesDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public Roles_authoritiesDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void configureEnd()
    {
        this.sessionFactory.close();
    }

    //    Add role authority
    public void addRoleAuthority(final int r_id, final int a_id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                try {
                    session.persist(new Roles_authorities(r_id,a_id));
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

    //    Delete authority from role
    public boolean deleteAuthority(final int r_id, final int a_id){
        Session session;

        session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            List<Roles_authorities> results;
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Roles_authorities> q1 = criteriaBuilder.createQuery(Roles_authorities.class);
            Root<Roles_authorities> root = q1.from(Roles_authorities.class);
            Predicate questionByBlankId = criteriaBuilder.equal(root.get("role_id"), r_id);
            results = session.createQuery(q1.where(questionByBlankId)).getResultList();
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i).getAuthority_id() == a_id){
                    System.out.println("FINDED");
                    session.delete(results.get(i));
                    session.getTransaction().commit();
                    return true;
                }
            }
        } catch (NoResultException e){

        }
        finally {
            session.close();
        }

        return false;
    }

    //    Delete all authorities from role
    public boolean deleteAllAuthority(final int r_id){
        Session session;

        session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            List<Roles_authorities> results;
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Roles_authorities> q1 = criteriaBuilder.createQuery(Roles_authorities.class);
            Root<Roles_authorities> root = q1.from(Roles_authorities.class);
            Predicate questionByBlankId = criteriaBuilder.equal(root.get("role_id"), r_id);
            results = session.createQuery(q1.where(questionByBlankId)).getResultList();
            for (int i = 0; i < results.size(); i++) {
                session.delete(results.get(i));
                return true;
            }
            session.getTransaction().commit();
        } catch (NoResultException e){

        }
        finally {
            session.close();
        }

        return false;
    }
}
