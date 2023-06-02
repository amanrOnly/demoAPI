package com.dtdl.demoAPI.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SaveException extends RuntimeException{
    String resourceName;

    public SaveException(String resourceName) {
        super(String.format("Error occurred while saving data in %s table", resourceName));
        this.resourceName = resourceName;
    }
}
