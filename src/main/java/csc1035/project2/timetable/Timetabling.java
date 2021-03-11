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

    public static List teacherModule(String moduleId){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List staff = session.createQuery("select S from staff S join S.teaches T where T.id = :id").setParameter("id",moduleId).list();

        session.getTransaction().commit();
        session.close();

        return staff;
    }

    public static List moduleRequirements(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List moduleRequirements = session.createQuery("from moduleRequirements ").list();

        session.getTransaction().commit();
        session.close();

        return moduleRequirements;
    }

    public static void main(String[] args) {
        System.out.println(studentsTakeModule("NTT2305"));

        System.out.println(teacherModule("NTT2305"));

        System.out.println(moduleRequirements());
    }
}
