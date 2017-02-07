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
    
    

}
