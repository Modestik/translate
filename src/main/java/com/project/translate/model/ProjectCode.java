package com.project.translate.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProjectCode {
    CODE200("Object added to dictionary", HttpStatus.OK),
    CODE1001("Object already exists", HttpStatus.BAD_REQUEST),
    CODE1002("Object cannot be translated ", HttpStatus.BAD_REQUEST),
    CODE1100("Internal error in code", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;


    ProjectCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
