package com.epam.catalog.service.tools;


public class ServiceTools {

    private static final ServiceTools instance = new ServiceTools();
    private ServiceTools(){}
    public static ServiceTools getInstance(){
        return instance;
    }

}
