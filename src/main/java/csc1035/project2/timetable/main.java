package csc1035.project2.timetable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import csc1035.project2.HibernateUtil;
import java.util.Iterator;
import java.util.List;

public class main {

    public static void main(String[] args) {
        /*Session session = HibernateUtil.getSessionFactory().openSession();
        //read
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List modules = session.createQuery("FROM modules.csv ").list();
            for (Iterator<Module> iterator = modules.iterator(); iterator.hasNext();){
                Module module = iterator.next();
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
        }*/

    }
}
