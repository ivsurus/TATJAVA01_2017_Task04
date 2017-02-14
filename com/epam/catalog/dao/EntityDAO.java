package com.epam.catalog.dao;



import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.exeption.DAOException;

import java.util.Set;


public interface EntityDAO<T> {
    void addEntity(T entity) throws DAOException;
    Set<T> findEntity(SearchRequest searchRequestObject) throws DAOException;
}
