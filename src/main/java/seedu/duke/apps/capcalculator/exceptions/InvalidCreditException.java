package seedu.duke.apps.capcalculator.exceptions;

/**
 * Signals that the given MC is not valid.
 */
public class InvalidCreditException extends CapCalculatorException {
    public InvalidCreditException(String errorMessage) {
        super(errorMessage);
    }
}
