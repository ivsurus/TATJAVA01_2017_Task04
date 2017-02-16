package com.epam.catalog.service.util;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.Movie;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.bean.parameter.EntityParameterName;
import com.epam.catalog.service.constant.ServiceConstant;
import com.epam.catalog.service.exeption.ServiceException;
import java.util.HashMap;
import java.util.Map;

public class ParameterValidator {

    private static Map<String, String> patternRepository = new HashMap<>();

     static {
        patternRepository.put(EntityParameterName.TITLE.toString(),
                ServiceConstant.TITLE_PATTERN);
        patternRepository.put(EntityParameterName.AUTHOR.toString(),
                ServiceConstant.AUTHOR_PATTERN);
        patternRepository.put(EntityParameterName.YEAR.toString(),
                ServiceConstant.YEAR_PATTERN);
        patternRepository.put(EntityParameterName.GENRE.toString(),
                ServiceConstant.GENRE_PATTERN);
        patternRepository.put(EntityParameterName.PRODUCER.toString(),
                ServiceConstant.PRODUCER_PATTERN);
        patternRepository.put(EntityParameterName.PRICE.toString(),
                ServiceConstant.PRICE_PATTERN);
    }

    private ParameterValidator(){}

    public static boolean validateSearchRequestParameters
            (SearchRequest searchRequestObject) throws ServiceException {

        String[]parameter = searchRequestObject.getRequestParameters()
                .split(ServiceConstant.DELIMITER);

        if (parameter.length!= 2 ){
            throw new ServiceException("Incorrect number of parameters");
        }

        String parameterName = parameter[0];
        String parameterValue = parameter[1];

        return validate(parameterName,parameterValue);
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

    private static boolean validate (String parameterName, String parameterValue){
        String pattern = patternRepository.get(parameterName);
        return parameterValue.matches(pattern);
    }

}
