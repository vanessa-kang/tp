package seedu.duke.apps.capcalculator.exceptions;

/**
 * Signals that the given CAP is not valid.
 */
public class InvalidCapException extends CapCalculatorException {
    public InvalidCapException(String errorMessage) {
        super(errorMessage);
    }
}
