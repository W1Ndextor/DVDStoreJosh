package dvdrental;
// Generated Jan 31, 2017 8:25:12 AM by Hibernate Tools 4.3.1



/**
 * FilmCategoryId generated by hbm2java
 */
public class FilmCategoryId  implements java.io.Serializable {


     private short filmId;
     private byte categoryId;

    public FilmCategoryId() {
    }

    public FilmCategoryId(short filmId, byte categoryId) {
       this.filmId = filmId;
       this.categoryId = categoryId;
    }
   
    public short getFilmId() {
        return this.filmId;
    }
    
    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }
    public byte getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(byte categoryId) {
        this.categoryId = categoryId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FilmCategoryId) ) return false;
		 FilmCategoryId castOther = ( FilmCategoryId ) other; 
         
		 return (this.getFilmId()==castOther.getFilmId())
 && (this.getCategoryId()==castOther.getCategoryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getFilmId();
         result = 37 * result + this.getCategoryId();
         return result;
   }   


}


