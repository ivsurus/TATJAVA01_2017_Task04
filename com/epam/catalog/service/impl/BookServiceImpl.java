package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.SearchRequest;
import com.epam.catalog.dao.EntityDAO;
import com.epam.catalog.dao.exeption.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.EntityService;
import com.epam.catalog.service.exeption.ServiceException;
import com.epam.catalog.service.util.ServiceConstant;

import java.util.*;

public class BookServiceImpl implements EntityService<Book> {


    @Override
    public void addEntity(Book book) throws ServiceException {
        boolean parametersAreValid = validateEntityParamenters(book);
        if (parametersAreValid) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            EntityDAO<Book> bookDAO = daoObjectFactory.getBookDAO();
            try {
                bookDAO.addEntity(book);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException(ServiceConstant.INVALID_PARAMETERS); //тут хорошо подумать
            // log
        }
    }

    // здесь обязательно валидировать входные параметры и передать книгу в DAO
    @Override
    public Set<Book> findEntity(SearchRequest searchRequestObject) throws ServiceException {
        boolean parametersAreValid = validateParameters(book);
        Set<Book> booksForUser;
        if (parametersAreValid) {
            try {
                booksForUser = bookDAO.findEntity(book);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Parameters are not valid");
            //log
        }
        return booksForUser;
   }

   //проверить на null
   private boolean validateEntityParamenters (Book book){
        return book.getAuthor().matches(ServiceConstant.AUTHOR_PATTERN) &&
                book.getTitle().matches(ServiceConstant.TITLE_PATTERN) &&
                book.getYear().matches(ServiceConstant.YEAR_PATTERN);
   }

    //author$%$pushkin                - request parameters
   private boolean validateSearchRequestParamenters (SearchRequest searchRequestObject){
       String parameterName = searchRequestObject.getRequestParameters()
                                .split(ServiceConstant.DELIMITER,2)[0];
       String parameterValue = searchRequestObject.getRequestParameters()
                                .split(ServiceConstant.DELIMITER,2)[1];
       return true;


       
   }
   /* private boolean validate (String pa){
        String parameterName = searchRequestObject.getRequestParameters()
                .split(ServiceConstant.DELIMITER,2)[0];
        String parameterValue = searchRequestObject.getRequestParameters()
                .split(ServiceConstant.DELIMITER,2)[1];
        return true;
    }*/




   /*private boolean validateParameters(Book book){
       if(!book.getTitle().equals(null)){
           if(!book.getTitle().matches(TITLE_PATTERN)){
               return false;
           }
       }
       if(!book.getAuthor().equals(null)){
           if(!book.getAuthor().matches(AUTHOR_PATTERN)){
               return false;
           }
       }
       //если дата будет d int то проверять нужно будет на 0
       if(!book.getYear().equals(null)){
           if(!book.getYear().matches(YEAR_PATTERN)){
               return false;
           }
       }
       return true;
   }*/

   /*//из строковых данных полученных с БД создаем сет сущностей при условии валидных параметров
   private Set<Book> createBooksBooksForUser (Set <String> books){
       Set<Book> booksForUser = new HashSet<>();
       for (String bookStr: books){
           boolean parametersAreValid = validateParameters(parseDataBaseResponse(bookStr));
           if (parametersAreValid){
               booksForUser.add(createBook());
           }
       }
       return booksForUser;
   }

   //выбор категории для поиска
   private Set<Book> selectBySearchCriterion(Set<Book> set, String searchCriterionValue,
                                                String searchCriterionCategory){
       switch (searchCriterionCategory) {
            case CASE_TITLE:
                System.out.println(1);
               return caseTitleExecution(set, searchCriterionValue);
            case CASE_AUTHOR:
                System.out.println(2);
                System.out.println(searchCriterionValue);
               return caseAuthorExecution(set, searchCriterionValue);
            case CASE_YEAR:
                System.out.println(3);
               return caseYearExecution(set, searchCriterionValue);
        }
       System.out.println(4);
        return set;
   }

   //поиск по названию
   private Set<Book> caseTitleExecution(Set<Book> set, String searchCriterionValue){
        Iterator<Book> iterator = set.iterator();
        while (iterator.hasNext()) {
               if (!iterator.next().getTitle().equals(searchCriterionValue)){
                   iterator.remove();
               }
        }
        return set;
   }

   //поиск по автору
   private Set<Book> caseAuthorExecution(Set<Book> set, String searchCriterionValue){
        Iterator<Book> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getAuthor().equals(searchCriterionValue)){
                iterator.remove();
            }
        }
        return set;
   }

   //поиск по году
   private Set<Book> caseYearExecution(Set<Book> set, String searchCriterionValue){
        Iterator<Book> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getYear().equals(searchCriterionValue)){
                iterator.remove();
            }
        }
        return set;
   }

   //находим критерий для поиска сущности в базе данных
   private String findSearchCriterionValue(String request){
        return request.split(DELIMITER)[1];
    }

    //находим критерий для поиска сущности в базе данных
   private String findSearchCriterionCategory(String request){
        return request.split(DELIMITER)[2];
    }


    //инициализируем сущность (книгу)
   private Book createBook(){
        Book book = new Book();
        book.setTitle(parameters[1]);
        book.setAuthor(parameters[2]);
        book.setYear(parameters[3]);
        return book;
   }

    //одна книга
    //парсим одну строчку из базы данных на парметры, добавляем параметры как значения и паттерны как ключи
   private Map<String,String> parseDataBaseResponse(String dataBaseResponse){
        Map<String,String> map = new HashMap<>();
        parameters = dataBaseResponse.split(DELIMITER);
        map.put(IDENTIFIER_PATTERN,parameters[0]);
        map.put(TITLE_PATTERN,parameters[1]);
        map.put(AUTHOR_PATTERN,parameters[2]);
        map.put(YEAR_PATTERN,parameters[3]);
        return map;
   }

    //получаем проверем все параметры для одной книги по паттернам
   private boolean validateParameters (Map map){
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> pair = iterator.next();
            if (!pair.getValue().matches(pair.getKey()))
            return false;
        }
        return true;
   }*/
}