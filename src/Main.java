import edu.cnm.deepdive.db.Event;
import edu.cnm.deepdive.db.Person;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {

  private static final SessionFactory ourSessionFactory;

  static {
    System.out.println("We want to say something first!!");
  }
  static {
    try {
      Configuration configuration = new Configuration();
      configuration.configure();

      ourSessionFactory = configuration.buildSessionFactory();
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static Session getSession() throws HibernateException {
    return ourSessionFactory.openSession();
  }

  public static void main(final String[] args) throws Exception {
    final Session session = getSession();
    try {
      Transaction tx = session.beginTransaction();
//      Event event = new Event();
//      event.setName("End of Time");
//      event.setStart(new Timestamp(System.currentTimeMillis()));
//      event.setFinish(new Timestamp(System.currentTimeMillis()));
//      session.save(event);
      Query query = session.createQuery("from Person where firstName like 'Chris%'");
      List<Person> persons = query.list();
      for (Person p : persons) {
        Collection<Event> events = p.getEventsById();
        for (Event ev : events) {
          System.out.println(ev.getName());
        }
      }
      tx.commit();
//      System.out.println("querying all the managed entities...");
//      final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//      for (EntityType<?> entityType : metamodel.getEntities()) {
//        final String entityName = entityType.getName();
//        final Query query = session.createQuery("from " + entityName);
//        System.out.println("executing: " + query.getQueryString());
//        for (Object o : query.list()) {
//          System.out.println("  " + o);
//        }
//      }
    } finally {
      session.close();
    }
  }
}