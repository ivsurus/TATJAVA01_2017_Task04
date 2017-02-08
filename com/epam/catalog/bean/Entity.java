package com.epam.catalog.bean;


import com.epam.catalog.bean.category.CategoryName;

public class Entity {

    public Entity(){}

    private CategoryName category;
    private String author;
    private String year;
    private String title;
    private String searchCriterionName;
    private String searchCriterionValue;

    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public String getYear(){
        return year;
    }
    public CategoryName getCategory(){
        return category;
    }

    public void setCategory(CategoryName category){
        this.category = category;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setYear(String year){
        this.year = year;
    }



    /*
    нужно переопределить с учетом новых полей
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
    }*/

}
