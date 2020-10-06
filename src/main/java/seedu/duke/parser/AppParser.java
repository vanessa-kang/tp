package seedu.duke.parser;

import seedu.duke.apps.AcademicPlanner;
import seedu.duke.apps.App;
import seedu.duke.apps.CapCalculator;
import seedu.duke.apps.ModuleInitializer;
import seedu.duke.exceptions.AppParserException;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

public class AppParser {
    public static App parse(String userInput, ModuleInitializer allModules, Person currentPerson, Ui ui)
            throws AppParserException {
        userInput = userInput.trim().toLowerCase();

        if (userInput.equals("acadplan")) {
            return new AcademicPlanner(allModules, currentPerson, ui);
        } else if (userInput.equals("capcalc")) {
            return new CapCalculator(currentPerson, ui);
        } else if (userInput.equals("exit")) {
            return new App(true);
        } else {
            throw new AppParserException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
