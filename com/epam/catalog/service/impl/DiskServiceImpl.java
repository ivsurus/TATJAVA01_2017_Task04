package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.util.ServiceConstant;
import com.epam.catalog.service.util.ServiceTool;
import java.util.Set;

public class DiskServiceImpl implements EntityService<Disk> {


    @Override
    public void addEntity(Disk disk) throws ServiceException {
        boolean parametersAreValid = ServiceTool.validateEntityParameters(disk);
        if (parametersAreValid) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            EntityDAO<Disk> diskDAO = daoObjectFactory.getDiskDAO();
            try {
                diskDAO.addEntity(disk);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException(ServiceConstant.INVALID_PARAMETERS);
            // log
        }
    }

    @Override
    public Set<Disk> findEntity(SearchRequest searchRequestObject) throws ServiceException {
        boolean parametersAreValid = ServiceTool.validateSearchRequestParameters(searchRequestObject);
        Set<Disk> diskSet;
        if (parametersAreValid) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            EntityDAO<Disk> diskDAO = daoObjectFactory.getDiskDAO();
            try {
                diskSet = diskDAO.findEntity(searchRequestObject);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException(ServiceConstant.INVALID_PARAMETERS);
            //log
        }
        return diskSet;
    }

}
