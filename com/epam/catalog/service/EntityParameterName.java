package com.epam.catalog.service;


public enum  EntityParameterName {
    AUTHOR, TITLE, YEAR


}
class exam{
    public static void main(String[] args) {
        System.out.println(EntityParameterName.valueOf("TITLE"));
        System.out.println(EntityParameterName.TITLE.toString());


    }
}