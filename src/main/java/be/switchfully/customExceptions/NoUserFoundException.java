package be.switchfully.customExceptions;

import jakarta.ws.rs.NotFoundException;

public class NoUserFoundException extends NotFoundException {
    public NoUserFoundException(String message) {
        super(message);
    }
}
