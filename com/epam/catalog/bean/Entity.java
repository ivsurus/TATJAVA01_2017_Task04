package com.epam.catalog.bean;


import com.epam.catalog.bean.category.CategoryName;

import java.io.Serializable;

public class Entity implements Serializable{


    private CategoryName category;
    private String author;
    private String year;
    private String title;

    public Entity() {}

    public final String getAuthor(){
        return author;
    }
    public final String getTitle(){
        return title;
    }
    public final String getYear(){
        return year;
    }
    public final CategoryName getCategory(){
        return category;
    }

    public final void setCategory(CategoryName category){
        this.category = category;
    }
    public final void setTitle(String title){
        this.title = title;
    }
    public final void setAuthor(String author){
        this.author = author;
    }
    public final void setYear(String year){
        this.year = year;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (category != entity.category) return false;
        if (author != null ? !author.equals(entity.author) : entity.author != null) return false;
        if (year != null ? !year.equals(entity.year) : entity.year != null) return false;
        return title != null ? title.equals(entity.title) : entity.title == null;

    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
