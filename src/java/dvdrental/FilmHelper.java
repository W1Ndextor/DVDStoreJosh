/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

/**
 *
 * @author Josh
 */
public class FilmHelper {

    Session session = null;

    //constructor
    public FilmHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getFilmTitles(int startID) {
        List<Film> filmList = null;

        String sql = "select * from film order by title limit :start, :end";

        try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);
            
            //associating the film table and film pojo to the query
            q.addEntity(Film.class);
            
            q.setParameter("start", startID);
            q.setParameter("end", 10);
            
            filmList = (List<Film>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmList;
    }
    
    public int getNumberFilms(){
        List<Film> filmList = null;

        String sql = "select * from film";
        
         try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);
            
            //associating the film table and film pojo to the query
            q.addEntity(Film.class);
            
            
            filmList = (List<Film>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return filmList.size();
    }
}
