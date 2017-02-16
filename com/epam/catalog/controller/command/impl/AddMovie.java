package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Movie;
import com.epam.catalog.controller.constant.ControllerConstant;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public final class AddMovie implements Command {


    @Override
    public String execute(String request) {
        Movie movie = new Movie();
        movie = initParameters(movie, request);
        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        EntityService<Movie> movieService = serviceObjectFactory.getMovieService();
        try{
            movieService.addEntity(movie);
        } catch (ServiceException e){
            return ControllerConstant.UNSUCCESSFUL_OPERATION;
        } catch (Exception e){
            return ControllerConstant.UNSUCCESSFUL_OPERATION;
        }
        return ControllerConstant.SUCCESSFUL_OPERATION;
    }

    private Movie initParameters (Movie movie, String request){
        String[] parameters = request.split(ControllerConstant.DELIMITER);
        movie.setProducer(parameters[1]);
        movie.setTitle(parameters[2]);
        movie.setGenre(parameters[3]);
        movie.setYear(parameters[4]);
        return movie;
    }

}
