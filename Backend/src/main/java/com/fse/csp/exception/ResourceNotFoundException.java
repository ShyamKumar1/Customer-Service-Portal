package com.fse.csp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private final long serialVersionUID= 1L;
    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String service_request, String id, Long serviceRequestId) {

    }
}
