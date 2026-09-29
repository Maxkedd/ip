package Exceptions;

/** Signals invalid command input with a message that can be shown to the user. */
public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String message) {
        super(message);
    }
}
