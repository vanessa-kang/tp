package seedu.duke.exceptions;

public class AcademicException extends Exception {
    private String errorMessage;

    private static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";

    public AcademicException(String errorMessage) {
        String stringToPrint = errorMessage + NEW_LINE + EXITING_CURRENT_COMMAND;
        this.errorMessage = stringToPrint;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
