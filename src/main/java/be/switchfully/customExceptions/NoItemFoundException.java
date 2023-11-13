package be.switchfully.customExceptions;

import jakarta.ws.rs.NotFoundException;

public class NoItemFoundException extends NotFoundException {
    public NoItemFoundException(String message) {
        super(message);
    }
}
