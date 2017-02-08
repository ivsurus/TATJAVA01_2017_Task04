package com.epam.catalog.dao.impl;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.tools.DataBaseTools;

import java.io.IOException;
import java.util.Set;


public class TxtDiskDAO implements EntityDAO<Disk>{

    private DataBaseTools dbTools = DataBaseTools.getInstance();
    private final String IDENTIFIER = "d";
    private final String DELIMITER = "$%$";

    @Override
    public void addEntity(Disk disk) throws DAOException {
        try {
            dbTools.writeToDB(IDENTIFIER+DELIMITER+disk.getTitle()+DELIMITER+disk.getAuthor()
                    +DELIMITER+disk.getYear()+"\n");
        } catch (IOException e){
            throw new DAOException(e);
        }
    }

    @Override
    public Set<String> findEntity() throws DAOException{
        try {
            return dbTools.delUnnecessaryData(dbTools.readFromDB(), IDENTIFIER);
        } catch (IOException e){
            throw new DAOException(e);
        }
    }
}
