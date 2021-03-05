package csc1035.project2.util;

import csc1035.project2.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Controller class providing a way to communicate with the Database
 *
 * @author Titas Janusonis
 */
public class Controller<E> implements IController<E> {

    @Override
    public void save(Object o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
    }

    @Override
    public void update(Object s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(s);
        session.getTransaction().commit();

    }

    @Override
    public Object getById(Class c, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Object result = c.cast(session.get(c, id));
        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<E> getAll(Class<E> c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<E> entries = null;
        entries = session.createQuery("from "+c.getClass().getSimpleName()).list();
        session.getTransaction().commit();
        return entries;
    }


    @Override
    public void delete(Class c, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Object entry = c.cast(session.get(c, id));
        session.delete(entry);
        session.getTransaction().commit();
    }

    @Override
    public void bulkListSave(List e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        for(Object i: e){
            session.persist(i);
        }
        session.getTransaction().commit();
    }
}
