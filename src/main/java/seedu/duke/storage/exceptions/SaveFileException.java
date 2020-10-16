package seedu.duke.storage.exceptions;

public class SaveFileException extends Exception {
    private String errorMessage;

    public SaveFileException (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
