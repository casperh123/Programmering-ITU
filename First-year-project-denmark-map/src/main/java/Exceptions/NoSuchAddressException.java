package Exceptions;

public class NoSuchAddressException extends Exception {

    String message;

    public NoSuchAddressException(String message) {
        super();
        this.message = message;
    }

    public String toString() {
        return "Could not find address: " + message;
    }
}
