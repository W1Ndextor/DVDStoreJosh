/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Josh
 */
public class FilmActorHelper {

    Session session = null;

    //constructor
    public FilmActorHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List getActors(){
        List<Actor> actorList = null;
        
        String sql = "SELECT * FROM ACTOR";
        
        try{
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(Actor.class);
            
            actorList = (List<Actor>) q.list();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return actorList;
    }
    
    public List getCategories(){
        List<Category> categoryList = null;
        
        String sql = "SELECT * FROM CATEGORY";
        
        try{
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(Category.class);
            
            categoryList = (List<Category>) q.list();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return categoryList;
    }
    
    public List getLanguages(){
        //declaring local variable to be returned by the method. The method will return a list of languages.
        List<Language> languageList = null;
        
        //creating an SQL query but as a string
        String sql = "SELECT * FROM LANGUAGE";
        
        try{
            //beginning a transaction if the vurrent transaction isnt active
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            //creating actual query that will e executed against the database
            SQLQuery q = session.createSQLQuery(sql);
            
            //associating the language POJO and table with the query
            q.addEntity(Language.class);
            
            //executing the query
            languageList = (List<Language>) q.list();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return languageList;
    }
    
    //this method is going to insert a film into the film table
    private int insertFilm(String title, String description, int language, String rating, Timestamp timeStamp){
        
        int result = 0;
        
        String sql = "insert into film "
                + "(title, description, language_id, rental_duration, rental_rate, "
                + "replacemtn_cost, rating, last_update) "
                + "values (:title, :description, :languageId, :rentalDuration, "
                + ":rentalRate, :replacementCost, :rating, :update)";
        
        try{
             //beginning a transaction if the vurrent transaction isnt active
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            //creating actual query that will e executed against the database
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(Film.class);
            
            q.setParameter("title", title);
            q.setParameter("description", description);
            q.setParameter("languageId", language);
            q.setParameter("rentalDuration", 3);
            q.setParameter("rentalRate", 4.99);
            q.setParameter("replacementCost", 19.99);
            q.setParameter("rating", rating);
            q.setParameter("update", timeStamp);
            
            q.executeUpdate();
            
            session.getTransaction().commit();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    //this method is going to return the film id of the last film inserted into the film table
    private int getFilmId(){
        List<Film> filmList = null;
        
        String sql = "select * from film order by last_update desc limit 1";
        
         try{
             //beginning a transaction if the vurrent transaction isnt active
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            //creating actual query that will e executed against the database
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(Film.class);
            
            filmList = (List<Film>) q.list();
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return filmList.get(0).getFilmId();
    }
    
    
    //this method is going to insert a film actor into the film actor table
    private int insertFilmActor (int actor, int film, Timestamp timeStamp){
        
        int result = 0;
        
        String sql = "insert into film_actor values (:actorId, :filmId, :update)";
        
         try{
             //beginning a transaction if the vurrent transaction isnt active
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            //creating actual query that will e executed against the database
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(FilmActor.class);
            
            q.setParameter("actorId", actor);
            q.setParameter("filmId", film);
            q.setParameter("update", timeStamp);
                     
            q.executeUpdate();
            
            session.getTransaction().commit();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
    
    //this method is going to insert a film_category into the film category table
    private int insertFilmCategory (int film, int category, Timestamp timeStamp){
        
        
            int result = 0;
        
        String sql = "insert into film_category values (:filmId, :categoryId, :update)";
        
         try{
             //beginning a transaction if the vurrent transaction isnt active
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            //creating actual query that will e executed against the database
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(FilmCategory.class);
            
            q.setParameter("categoryId", category);
            q.setParameter("filmId", film);
            q.setParameter("update", timeStamp);
                     
            q.executeUpdate();
            
            session.getTransaction().commit();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
    
    public int insert (String title, String description, int actor, int category,
            int language, String rating, Timestamp timeStamp){
        
        
        int result = 0;
        
        int filmResult = insertFilm(title, description, language, rating, timeStamp);
        int filmId = getFilmId();
        int actorResult = insertFilmActor(actor, filmId, timeStamp);
        int categoryResult = insertFilmCategory(filmId, category, timeStamp);
        
        if (filmResult == 1 && actorResult == 1 && categoryResult == 1){
            result = 1;
        }
        return result;
    }
    
    
    

}
