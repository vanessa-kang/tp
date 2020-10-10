package seedu.duke.exceptions;

public class CommandParserException extends Exception {
    String errorMessage;

    public CommandParserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
