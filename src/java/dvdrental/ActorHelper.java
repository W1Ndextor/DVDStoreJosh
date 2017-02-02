package dvdrental;

//imports
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Josh
 */
public class ActorHelper {

    Session session = null;

    //constructor
    public ActorHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertActor(Actor a) {
        int result = 0;

        //create sql statement as a string
        //:fName, :lName, :update are place holders for actual names
        String sql = "insert into actor(first_name, last_name, last_update) values (:fName, :lName, :update)";

        //checking to make sure the transaction is active (all queries need to be wrapped up inside transactions)
        //if it isnt active we start it
        try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            //converting our string query into an actual query that can be executed for the database
            SQLQuery q = session.createSQLQuery(sql);
            //associating actor POJO and table with the query
            q.addEntity(Actor.class);
            //binding values to the placeholders in the query
            q.setParameter("fName", a.getFirstName());
            q.setParameter("lName", a.getLastName());
            q.setParameter("update", a.getLastUpdate());

            //exectuing the query
            result = q.executeUpdate();

            //committing the query on the database
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
