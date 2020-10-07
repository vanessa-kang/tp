package seedu.duke.exceptions;

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
