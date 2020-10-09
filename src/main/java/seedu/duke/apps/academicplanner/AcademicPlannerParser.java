package seedu.duke.apps.academicplanner;

import seedu.duke.apps.ModuleInitializer;
import seedu.duke.apps.academicplanner.commands.AddModuleCommand;
import seedu.duke.apps.academicplanner.commands.EditModuleCommand;
import seedu.duke.apps.academicplanner.commands.PrintCalenderCommand;
import seedu.duke.apps.academicplanner.commands.RemoveModuleCommand;
import seedu.duke.exceptions.CommandParserException;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class AcademicPlannerParser {
    private static final int MODULE_CODE_INDEX = 1;
    public static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String ADD_COMMAND = "ADD";
    private static final String EDIT_COMMAND = "EDIT";
    private static final String REMOVE_COMMAND = "REMOVE";
    private static final String VIEW_COMMAND = "VIEW";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String EXIT_MESSAGE = "Thank you for using Academic Planner!";
    private static final String COMMANDS_LIST = "Available commands are:\n"
            + "\tadd <module code>\n"
            + "\tedit <module code>\n"
            + "\tremove <module code>\n"
            + "\tview\n"
            + "\texit\n"
            + "Type a command to continue...";

    public static Command parse(String userInput, ModuleInitializer allModules, Person currentPerson, Ui ui)
        throws CommandParserException {
        String[] inputs = userInput.toUpperCase().split(" ");
        Scanner in = ui.getScanner();

        if (inputs[0].equals(ADD_COMMAND)) {
            return new AddModuleCommand(allModules, currentPerson, in, inputs[MODULE_CODE_INDEX]);
        } else if (inputs[0].equals(EDIT_COMMAND)) {
            return new EditModuleCommand(allModules, currentPerson, in, inputs[MODULE_CODE_INDEX]);
        } else if (inputs[0].equals(REMOVE_COMMAND)) {
            return new RemoveModuleCommand(allModules, currentPerson, in, inputs[MODULE_CODE_INDEX]);
        } else if (inputs[0].equals(VIEW_COMMAND)) {
            System.out.println("Sorry this function is not ready yet!");
            return new PrintCalenderCommand();
            //TODO Add in print calender
        } else if (inputs[0].equals(EXIT_COMMAND)) {
            return new Command(true);
        } else {
            throw new CommandParserException(ERROR_INVALID_COMMAND + NEW_LINE + EXITING_CURRENT_COMMAND);
        }
    }
}
