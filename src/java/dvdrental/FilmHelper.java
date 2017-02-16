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
    
    public List getActorsByID (int filmId){
        
        
        
        List<Actor> actorList = null;
        String sql = "select * from actor, film_actor, film "
                + "where actor.actor_id = film_actor.actor_id "
                + "and film_actor.film_id = film.film_id "
                + "and film.film_id = :id";
        
        
        try {
            //if the sessions not active then we make it active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            //creating the actual query that will be executed against the database, up to this point out query has just been a string
            SQLQuery q = session.createSQLQuery(sql);
            
            //associating the actor table and actor pojo to the query
            q.addEntity(Actor.class);
            
            //binding a value to the placeholder in the query
            q.setParameter("id", filmId);
            
            //adding the results of the query to a list which we cast to a list of actors to return
            actorList = (List<Actor>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return actorList;
    }
    
    public Category getCategoryByID (int filmId){
        List<Category> categoryList = null;
        
        String sql = "select * from category, film_category, film "
                + "where category.category_id = film_category.category_id "
                + "and film_category.film_id = film.film_id "
                + "and film.film_id = :id";
        
         try {
            //if the sessions not active then we make it active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            //creating the actual query that will be executed against the database, up to this point out query has just been a string
            SQLQuery q = session.createSQLQuery(sql);
            
            //associating the actor table and actor pojo to the query
            q.addEntity(Category.class);
            
            //binding a value to the placeholder in the query
            q.setParameter("id", filmId);
            
            //adding the results of the query to a list which we cast to a list of actors to return
            categoryList = (List<Category>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return categoryList.get(0);
    }
    
    public Film getFilmDetails (int filmId){
        Film film = null;
        
        String sql = "select * from film where film_id = :id";
        
        try {
            //if the sessions not active then we make it active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            //creating the actual query that will be executed against the database, up to this point out query has just been a string
            SQLQuery q = session.createSQLQuery(sql);
            
            //associating the actor table and actor pojo to the query
            q.addEntity(Film.class);
            
            //binding a value to the placeholder in the query
            q.setParameter("id", filmId);
            
            //adding the results of the query to a list which we cast to a list of actors to return
            film = (Film) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
                
                
                return film;
    }
    
    public String getLangByID (int langId){
        
        Language language = null;
        
        String sql = "select * from language where language_id = :id";
        
        try {
            //if the sessions not active then we make it active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            //creating the actual query that will be executed against the database, up to this point out query has just been a string
            SQLQuery q = session.createSQLQuery(sql);
            
            //associating the actor table and actor pojo to the query
            q.addEntity(Language.class);
            
            //binding a value to the placeholder in the query
            q.setParameter("id", langId);
            
            //adding the results of the query to a list which we cast to a list of actors to return
            language = (Language) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return language.getName();
    }
    
}
