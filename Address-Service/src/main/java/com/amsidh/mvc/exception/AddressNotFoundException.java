package com.amsidh.mvc.exception;

public class AddressNotFoundException extends RuntimeException {
    private static final String ADDRESS_NOT_FOUND = "address with id %d not found";

    public AddressNotFoundException(Long id) {
        super(String.format(ADDRESS_NOT_FOUND, id));
    }

}
