package com.example.demo.webclient.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends RuntimeException {

    private String errorMessage;

    public ServerException(String errorMessage, String message) {
        super(message);
        this.errorMessage = errorMessage;
    }

}
