package com.epam.catalog.service.impl;


import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;

import java.util.Set;

public class DiskServiceImpl implements EntityService<Disk> {


    //обязательно проверить входные параметры
    @Override
    public void addEntity(Disk disk) throws ServiceException{
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        EntityDAO<Disk> diskDAO = daoObjectFactory.getDiskDAO();
        try{
            diskDAO.addEntity(disk);
        } catch (DAOException e){
            throw new ServiceException(e);
        }

    }

    //обязательно проверить входные параметры
    @Override
    public Set<Disk> findEntity(SearchRequest searchRequestObject) throws ServiceException {
        return null;
    }


}
