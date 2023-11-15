package be.switchfully.customExceptions;

import jakarta.ws.rs.NotFoundException;

public class NoOrderFoundException extends NotFoundException {
    public NoOrderFoundException(String message) {
        super(message);
    }
}
