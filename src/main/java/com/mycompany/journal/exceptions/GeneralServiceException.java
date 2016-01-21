package com.mycompany.journal.exceptions;


import com.mycompany.journal.db.model.DomainObject;
import com.mycompany.journal.services.GenericService;


public class GeneralServiceException extends RuntimeException {

    //add class which caused the exception

    public GeneralServiceException() {
        super();
    }

    public GeneralServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralServiceException(String message) {
        super(message);
    }

    public GeneralServiceException(Throwable cause) {
        super(cause);
    }

}
