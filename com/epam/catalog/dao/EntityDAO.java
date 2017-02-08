package com.epam.catalog.dao;



import com.epam.catalog.dao.exeption.DAOException;

import java.util.Set;


// параметризированный интерфейс

public interface EntityDAO<T> {
    void addEntity(T entity) throws DAOException;
    Set<String> findEntity() throws DAOException;
}
