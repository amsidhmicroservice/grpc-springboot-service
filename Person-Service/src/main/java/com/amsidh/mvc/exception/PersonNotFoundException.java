package com.amsidh.mvc.exception;

public class PersonNotFoundException extends RuntimeException {
    private static final String PERSON_NOT_FOUND = "person with id %d not found";

    public PersonNotFoundException(Long id) {
        super(String.format(PERSON_NOT_FOUND, id));
    }

}
