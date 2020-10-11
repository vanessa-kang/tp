package seedu.duke.exceptions;

/**
 * Signals that an invalid command has been given in the Academic Planner Parser.
 */
public class AcademicPlannerParserException extends Exception {

    String errorMessage;

    public AcademicPlannerParserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
