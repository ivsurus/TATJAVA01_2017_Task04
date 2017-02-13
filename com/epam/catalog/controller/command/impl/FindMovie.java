package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Movie;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.controller.util.ControllerConstant;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.Set;

public class FindMovie implements Command {

    @Override
    public String execute(String request) {

        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Movie> movieService = serviceObjectFactory.getMovieService();
        SearchRequest searchRequestObject = new SearchRequest();
        searchRequestObject = initParameters(searchRequestObject,request);
        Set movieSet;
        try{
            movieSet =  movieService.findEntity(searchRequestObject);
        } catch (ServiceException e){
            return ControllerConstant.UNSUCCESSFUL_OPERATION;
            //log
        }
        return createResponseForUser(movieSet);
    }

    private String createResponseForUser(Set<Movie> movieSet){
        StringBuilder builder = new StringBuilder();
        for (Movie movie: movieSet){
            builder.append(ControllerConstant.PRODUCER + movie.getProducer()+"\n");
            builder.append(ControllerConstant.TITLE + movie.getTitle()+"\n");
            builder.append(ControllerConstant.GENRE + movie.getGenre()+"\n");
            builder.append(ControllerConstant.YEAR + movie.getYear()+"\n\n");
        }
        return builder.toString();
    }

    //"find_book$%$author$%$pushkin"  - all request
    //author$%$pushkin                - request parameters

    //сделать методом бина или сервисным методом
    private SearchRequest initParameters (SearchRequest searchRequestObject, String request){
        searchRequestObject.setRequestParameters(request.split(ControllerConstant.DELIMITER,2)[1]);
        return searchRequestObject;
    }
}
