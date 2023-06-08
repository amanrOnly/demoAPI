package com.dtdl.demoAPI.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    String functionName;
    int line;

    public ResourceNotFoundException(String functionName, int line) {
        super(String.format("Problem in line %d of function %s", line, functionName));
        this.functionName = functionName;
        this.line = line;
    }
}
