package seedu.duke.parser;

import seedu.duke.apps.AcademicPlanner;
import seedu.duke.apps.App;
import seedu.duke.apps.CapCalculator;
import seedu.duke.apps.ModuleInitializer;
import seedu.duke.exceptions.AppParserException;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

public class AppParser {

    private static final String ACADEMIC_PLAN_COMMAND = "acadplan";
    private static final String CAP_CALCULATOR_COMMAND = "capcalc";
    private static final String EXIT_COMMAND = "exit";
    private static final String INVALID_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static App parse(String userInput, ModuleInitializer allModules, Person currentPerson, Ui ui)
            throws AppParserException {
        userInput = userInput.trim().toLowerCase();

        if (userInput.equals(ACADEMIC_PLAN_COMMAND)) {
            return new AcademicPlanner(allModules, currentPerson, ui);
        } else if (userInput.equals(CAP_CALCULATOR_COMMAND)) {
            return new CapCalculator(currentPerson, ui);
        } else if (userInput.equals(EXIT_COMMAND)) {
            return new App(true);
        } else {
            throw new AppParserException(INVALID_COMMAND_MESSAGE);
        }
    }
}
