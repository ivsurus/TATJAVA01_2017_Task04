package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Movie;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.Set;

public class FindMovie implements Command {

    private Set movies;
    private StringBuilder builder = new StringBuilder();
    private ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
    private EntityService<Movie> movieService = serviceObjectFactory.getMovieService();
    private final String TITLE = "Title: ";
    private final String AUTHOR = "Author: ";
    private final String YEAR = "Year: ";
    private final String FAILURE = "Service is currently not available";

    @Override
    public String execute(String request) {
        try{
            movies =  movieService.findEntity(request);
        } catch (ServiceException e){
            return FAILURE;
        }
        return createResponseForUser(movies);
    }

    private String createResponseForUser(Set<Movie> movies){
        for (Movie movie: movies){
            builder.append(TITLE + movie.getTitle()+"\n");
            builder.append(AUTHOR + movie.getAuthor()+"\n");
            builder.append(YEAR + movie.getYear()+"\n\n");
        }
        return builder.toString();
    }
}
