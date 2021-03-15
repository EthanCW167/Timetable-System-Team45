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

        Session session = HibernateUtil.getSessionFactory().openSession(); // Establish connection with database
        session.beginTransaction();

        List students = session.createQuery("select S from students S join S.takes T where T.id = :id").setParameter("id",moduleId).list();
        // Query database for students that take module and add to list

        session.getTransaction().commit();
        session.close(); // Close connection with database

        return students; // return array of students

    }

    /**
     * This method is responsible for creating and returning a list of teachers that teach a particular module
     * @param moduleId The id of the module that the user wishes to retrieve relevant data from
     * @return A list of teachers that teach the module found in the parameter
     */

    public static List teacherModule(String moduleId){

        Session session = HibernateUtil.getSessionFactory().openSession(); // Establish connection with database
        session.beginTransaction();

        List staff = session.createQuery("select S from staff S join S.teaches T where T.id = :id").setParameter("id",moduleId).list();
        // Query database for staff that teach module

        session.getTransaction().commit();
        session.close(); // Close connection with database

        return staff; // return array of staff
    }

    /**
     * This method is responsible for returning a list of all module requirements
     * @return array containing module requirements
     */

    public static List moduleRequirements(){

        Session session = HibernateUtil.getSessionFactory().openSession(); // Establish connection with database
        session.beginTransaction();

        List moduleRequirements = session.createQuery("from moduleRequirements ").list();
        // Query database for all module requirements

        session.getTransaction().commit();
        session.close(); // Close connection with database

        return moduleRequirements; // return array of module requirements
    }

    /**
     * this method takes a parameter moduleId and returns the module requirements for that id
     * @param moduleId ID of the module that the requirements for will be retrieved
     * @return List containing the module requirements for the particular module
     */

   public static List specificModuleRequirements(String moduleId){

       Session session = HibernateUtil.getSessionFactory().openSession(); // Establish connection with database
       session.beginTransaction();

       List moduleRequirements = session.createQuery("from moduleRequirements where id = :id").setParameter("id",moduleId).list();
       // Query database for specific module requirements

       session.getTransaction().commit();
       session.close(); // Close connection with database

       return moduleRequirements; // return array of module requirements for particular module
   }
}
