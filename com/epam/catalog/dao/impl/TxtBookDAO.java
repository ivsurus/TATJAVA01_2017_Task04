package com.epam.catalog.dao.impl;



import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.tools.DataBaseTools;
import java.io.IOException;
import java.util.Set;


public class TxtBookDAO implements EntityDAO<Book> {

    private DataBaseTools dbTools = DataBaseTools.getInstance();
    private final String IDENTIFIER = "b";
    private final String DELIMITER = "$%$";

    @Override
    public void addEntity(Book book) throws DAOException {
        try {
            dbTools.writeToDB(IDENTIFIER+DELIMITER+book.getTitle()+DELIMITER+book.getAuthor()
                                +DELIMITER+book.getYear()+"\n");
        } catch (IOException e){
            throw new DAOException(e);
        }
    }

    @Override
    public Set<String> findEntity() throws DAOException {
        try {
            return dbTools.delUnnecessaryData(dbTools.readFromDB(), IDENTIFIER);
        } catch (IOException e){
            throw new DAOException(e);
        }
    }

}

