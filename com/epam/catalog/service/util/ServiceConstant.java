package com.epam.catalog.service.util;


public final class ServiceConstant {

    public final static String TITLE_PATTERN = "[0-9A-Za-zА-Яа-яЁё]{1,30}[ ]{0,1}[A-Za-zА-Яа-яЁё-]{0,20}";
    public final static String AUTHOR_PATTERN = "[A-Za-zА-Яа-яЁё-]{1,20}[ ]{0,1}[A-Za-zА-Яа-яЁё-]{0,20}";
    public final static String YEAR_PATTERN = "^[12][0-9]{3}$"; //Years from 1000 to 2999
    public final static String PRICE_PATTERN = "[0-9]+";
    public final static String GENRE_PATTERN = "[0-9A-Za-zА-Яа-яЁё]{1,30}";
    public final static String PRODUCER_PATTERN = "[A-Za-zА-Яа-яЁё-]{1,20}[ ]{0,1}[A-Za-zА-Яа-яЁё-]{0,20}";
    public final static String INVALID_PARAMETERS =  "Parameters are not valid";
    public final static String DELIMITER = "%_%";
}
