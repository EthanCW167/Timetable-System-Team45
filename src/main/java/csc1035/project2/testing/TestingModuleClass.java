package csc1035.project2.testing;

import csc1035.project2.timetable.Module;
import csc1035.project2.timetable.Person.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import csc1035.project2.HibernateUtil;

import java.util.List;

/**
 * Test class for Module
 *
 * @author Ethan Wilson
 */
public class TestingModuleClass {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        //read
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List modules = session.createQuery("FROM modules ").list();
            for (Module module : (Iterable<Module>) modules ){
                System.out.println("---------------------------------------------------------");
                System.out.print("Name: " + module.getName());
                System.out.print("Credits: " + module.getCredits());
                System.out.println("Weeks: " + module.getWeeks());
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
