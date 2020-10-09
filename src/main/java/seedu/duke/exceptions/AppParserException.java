package seedu.duke.exceptions;

public class AppParserException extends Exception {
    String errorMessage;

    public AppParserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
