package com.example.adressparsing;

public class InvalidAdressException extends Exception {

    private String address;

    public InvalidAdressException(String address) {
        super();
        this.address = address;
    }

    public String getMessage() {
        return super.getMessage() + " Invalid address: " + address;
    }
}
