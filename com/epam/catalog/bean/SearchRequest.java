package com.epam.catalog.bean;


import java.io.Serializable;

public class SearchRequest implements Serializable {

    private String searchRequest;

    public SearchRequest(){}

    public final String getSearchRequest(){
        return searchRequest;
    }
    public final void setSearchRequest(String searchRequest){
        this.searchRequest = searchRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchRequest that = (SearchRequest) o;

        return searchRequest != null ? searchRequest.equals(that.searchRequest) : that.searchRequest == null;

    }

    @Override
    public int hashCode() {
        return searchRequest != null ? searchRequest.hashCode() : 0;
    }
}
