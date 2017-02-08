package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Movie;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class AddMovie implements Command {

    private final String DELIMITER = "\\$%\\$";
    private final String SUCCESS = "The movie was successfully added to the catalog";
    private final String FAILURE = "Service is currently not available";

    @Override
    public String execute(String request) {
        Movie movie = new Movie();
        String[] parameters = request.split(DELIMITER);
        movie.setTitle(parameters[1]);
        movie.setAuthor(parameters[2]);
        movie.setYear(parameters[3]);
        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Movie> movieService = serviceObjectFactory.getMovieService();
        try{
            movieService.addEntity(movie);
        } catch (ServiceException e){
            return FAILURE;
        }
        return SUCCESS;
    }
}
