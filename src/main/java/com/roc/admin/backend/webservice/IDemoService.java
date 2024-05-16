package com.roc.admin.backend.webservice;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "IDemoService", targetNamespace = "http://webservice.backend.admin.roc.com")
public interface IDemoService {
    @WebMethod
    String emrService(@WebParam String data);
}
