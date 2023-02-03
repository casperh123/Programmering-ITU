package com.example.adressparsing;

public class AdressNotFoundException extends Exception {

    private String adress;

    public AdressNotFoundException(String adress) {
        super();
        this.adress = adress;
    }

    public String getMessage() {
        return adress + " Was not found";
    }

}
