package csc1035.project2.timetable;



import java.util.List;
import csc1035.project2.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * This class is responsible for managing aspects of the timetabling system, it has methods for the returning of
 * a list of students that take a module, a list of teachers that take a particular module and a list of module
 * requirements.
 *
 * @Ethan Wilson
 */

public class Timetabling {

    /**
     * This method is responsible for creating and returning a list of students that take a particular module.
     * @param moduleId The id of the module that the user wishes to retrieve relevant data from
     * @return A list of students that take the module found in the parameter
     */

    public static List studentsTakeModule(String moduleId){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List students = session.createQuery("select S from students S join S.takes T where T.id = :id").setParameter("id",moduleId).list();

        session.getTransaction().commit();
        session.close();

        return students;

    }

    /**
     * This method is responsible for creating and returning a list of teachers that teach a particular module
     * @param moduleId The id of the module that the user wishes to retrieve relevant data from
     * @return A list of teachers that teach the module found in the parameter
     */

    public static List teacherModule(String moduleId){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List staff = session.createQuery("select S from staff S join S.teaches T where T.id = :id").setParameter("id",moduleId).list();

        session.getTransaction().commit();
        session.close();

        return staff;
    }

    /**
     * This module is responsible for returning a list of all the module requirements
     * @return A list of module requirements
     */

    public static List moduleRequirements(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List moduleRequirements = session.createQuery("from moduleRequirements ").list();

        session.getTransaction().commit();
        session.close();

        return moduleRequirements;
    }

}
