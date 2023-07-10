package shapestone.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shapestone.hibernate.model.Student;
import shapestone.hibernate.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        
        Session openSession = sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        openSession.save(new Student("Nithish", "Rayapati", "nithish@rediff.com"));
        openSession.save(new Student("Abhilash", "Dumma", "adumma@rediff.com"));

        beginTransaction.commit();
        System.out.println("Done with inserstion..");
    }
}
