package com.epam.catalog.bean;


import java.io.Serializable;

public class Disk  extends Entity implements Serializable{

    private String price;

    public Disk(){}

    public final String getPrice(){
        return price;
    }
    public final void setPrice(String price){
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disk disk = (Disk) o;

        return price != null ? price.equals(disk.price) : disk.price == null;

    }

    @Override
    public int hashCode() {
        return price != null ? price.hashCode() : 0;
    }
}
