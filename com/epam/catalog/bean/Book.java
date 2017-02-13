package com.epam.catalog.bean;


import java.io.Serializable;

public class Book extends Entity implements Serializable {

    private String author;

    public Book(){}

    public final String getAuthor(){
        return author;
    }
    public final void setAuthor(String author){
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return author != null ? author.equals(book.author) : book.author == null;

    }

    @Override
    public int hashCode() {
        return author != null ? author.hashCode() : 0;
    }
}

