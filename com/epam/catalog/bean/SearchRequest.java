package com.epam.catalog.bean;


import java.io.Serializable;

public class SearchRequest implements Serializable {

    private String requestParameters;

    public SearchRequest(){}

    public final String getRequestParameters(){
        return requestParameters;
    }
    public final void setRequestParameters(String requestParameters){
        this.requestParameters = requestParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchRequest that = (SearchRequest) o;

        return requestParameters != null ? requestParameters.equals(that.requestParameters) : that.requestParameters == null;

    }

    @Override
    public int hashCode() {
        return requestParameters != null ? requestParameters.hashCode() : 0;
    }
}
