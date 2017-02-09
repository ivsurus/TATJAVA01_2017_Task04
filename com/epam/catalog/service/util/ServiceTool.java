package com.epam.catalog.service.util;


public class ServiceTool {

    private static final ServiceTool instance = new ServiceTool();
    private ServiceTool(){}
    public static ServiceTool getInstance(){
        return instance;
    }

}
