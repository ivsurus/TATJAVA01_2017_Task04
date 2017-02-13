package com.epam.catalog.service;

import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.service.exeption.ServiceException;

import java.util.Set;


public interface EntityService<T> {
    void addEntity(T entity) throws ServiceException;
    Set<T> findEntity(SearchRequest searchRequestObject) throws ServiceException;
}
