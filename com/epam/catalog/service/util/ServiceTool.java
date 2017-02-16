package com.epam.catalog.service.util;


import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.util.DAOTool;
import com.epam.catalog.service.exeption.ServiceException;

public class ServiceTool {

    private ServiceTool(){}

    public static void destroy() throws ServiceException {
        try {
            DAOTool.destroy();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static void init() throws ServiceException {
        try {
            DAOTool.init();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
