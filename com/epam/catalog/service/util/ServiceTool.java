package com.epam.catalog.service.util;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.Movie;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.util.DAOTool;
import com.epam.catalog.service.EntityParameterName;
import com.epam.catalog.service.exeption.ServiceException;

import java.util.Enumeration;
import java.util.Map;

public class ServiceTool {

    public static boolean validateSearchRequestParameters(SearchRequest searchRequestObject){
        String parameterName = searchRequestObject.getRequestParameters()
                .split(ServiceConstant.DELIMITER,2)[0];
        String parameterValue = searchRequestObject.getRequestParameters()
                .split(ServiceConstant.DELIMITER,2)[1];
        return validate(parameterName,parameterValue);
    }


    public static boolean validate (String parameterName, String parameterValue){
        String pattern = getPattern(parameterName);
        return parameterValue.matches(pattern);
    }

    public static String getPattern (String parameterName){
        PatternRepository PatternRepositoryObject = PatternRepository.getInstance();
        Map<Enumeration, String> patternRepository = PatternRepositoryObject.getPatternRepository();
        return patternRepository.get(EntityParameterName.valueOf(parameterName));
    }

    public static boolean validateEntityParameters(Movie movie){
        return movie.getProducer().matches(ServiceConstant.PRODUCER_PATTERN) &&
                movie.getTitle().matches(ServiceConstant.TITLE_PATTERN) &&
                movie.getGenre().matches(ServiceConstant.GENRE_PATTERN)&&
                movie.getYear().matches(ServiceConstant.YEAR_PATTERN);
    }

    public static boolean validateEntityParameters(Disk disk){
        return disk.getPrice().matches(ServiceConstant.PRICE_PATTERN) &&
                disk.getTitle().matches(ServiceConstant.TITLE_PATTERN) &&
                disk.getGenre().matches(ServiceConstant.GENRE_PATTERN)&&
                disk.getYear().matches(ServiceConstant.YEAR_PATTERN);
    }

    public static boolean validateEntityParameters(Book book){
        return book.getAuthor().matches(ServiceConstant.AUTHOR_PATTERN) &&
                book.getTitle().matches(ServiceConstant.TITLE_PATTERN) &&
                book.getGenre().matches(ServiceConstant.GENRE_PATTERN)&&
                book.getYear().matches(ServiceConstant.YEAR_PATTERN);
    }

    public static void destroy() throws ServiceException {
        try {
            DAOTool.destroy();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
