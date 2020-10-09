package seedu.duke.parser;

import seedu.duke.apps.CapCalculator;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

public class CapCalculatorParser {

    private static final String CURRENT_COMMAND = "current";
    private static final String SET_TARGET_COMMAND = "set target";
    private static final String EXIT_COMMAND = "exit";
    private static final String INVALID_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static CapCalculatorParser parse(String userInput, Person currentPerson, Ui ui) {
        userInput = userInput.trim().toLowerCase();

        if (userInput.equals(CURRENT_COMMAND)) {
            return new CapCalculatorCurrentCommand();
        } else if (userInput.equals(SET_TARGET_COMMAND)) {
            return new SetTargetCommand();
        } else if (userInput.equals(EXIT_COMMAND)) {
            return new CapCalculatorParser(true);
        } else {
            throw new 
        }
    }
}
