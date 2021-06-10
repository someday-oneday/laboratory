package com.codewhy.common.web;


public class ServiceException extends RuntimeException{
    public ServiceException(){
        super();
    }
    public ServiceException(String errorMsg){
        super(errorMsg);
    }
    public ServiceException(Throwable e){
        super(e);
    }


}
