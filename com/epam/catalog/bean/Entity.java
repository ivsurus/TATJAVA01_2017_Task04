package com.epam.catalog.bean;



import java.io.Serializable;

public class Entity implements Serializable{


    private String genre;
    private String year;
    private String title;

    public Entity() {}

    public final String getGenre(){
        return genre;
    }
    public final String getTitle(){
        return title;
    }
    public final String getYear(){
        return year;
    }


    public final void setTitle(String title){
        this.title = title;
    }
    public final void setGenre(String genre){
        this.genre = genre;
    }
    public final void setYear(String year){
        this.year = year;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (genre != null ? !genre.equals(entity.genre) : entity.genre != null) return false;
        if (year != null ? !year.equals(entity.year) : entity.year != null) return false;
        return title != null ? title.equals(entity.title) : entity.title == null;

    }

    @Override
    public int hashCode() {
        int result = genre != null ? genre.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
