package com.dtdl.demoAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ServerException extends RuntimeException{
    String message;
    String cwd;

    public ServerException(String message, String cwd){
        this.message = message;
        this.cwd = cwd;
    }
}
