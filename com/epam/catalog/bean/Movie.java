package com.epam.catalog.bean;


import java.io.Serializable;

public class Movie extends Entity implements Serializable{

    private String producer;

    public Movie(){}

    public final String getProducer(){
        return producer;
    }

    public final void setProducer (String producer){
        this.producer = producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return producer != null ? producer.equals(movie.producer) : movie.producer == null;

    }

    @Override
    public int hashCode() {
        return producer != null ? producer.hashCode() : 0;
    }
}
