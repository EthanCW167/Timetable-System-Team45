package csc1035.project2.timetable;

import java.util.List;
import csc1035.project2.HibernateUtil;
import org.hibernate.Session;


public class Timetabling {

    public static List studentsTakeModule(String moduleId){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List students = session.createQuery("select S from students S join S.takes T where T.id = :id").setParameter("id",moduleId).list();

        session.getTransaction().commit();
        session.close();

        return students;



    }

}