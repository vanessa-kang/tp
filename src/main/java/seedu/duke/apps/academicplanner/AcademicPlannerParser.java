package seedu.duke.apps.academicplanner;


import seedu.duke.apps.academicplanner.commands.AddModuleCommand;
import seedu.duke.apps.academicplanner.commands.EditModuleCommand;
import seedu.duke.apps.academicplanner.commands.ModuleDetailsCommand;
import seedu.duke.apps.academicplanner.commands.PrintCalenderCommand;
import seedu.duke.apps.academicplanner.commands.PrintHelpCommand;
import seedu.duke.apps.academicplanner.commands.RemoveModuleCommand;
import seedu.duke.apps.academicplanner.commands.SearchModuleCommand;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.exceptions.CommandParserException;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;
import java.util.Scanner;

/**
 * Class representing the parser for the academic parser. Used in the academic parser app.
 */
public class AcademicPlannerParser {
    private static final int COMMAND_INDEX = 0;
    private static final int MODULE_CODE_INDEX = 1;
    private static final int CORRECT_COMMAND_LENGTH = 2;
    public static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String ADD_COMMAND = "ADD";
    private static final String EDIT_COMMAND = "EDIT";
    private static final String REMOVE_COMMAND = "REMOVE";
    private static final String VIEW_COMMAND = "VIEW";
    private static final String SEARCH_COMMAND = "SEARCH";
    private static final String HELP_COMMAND = "HELP";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String DETAILS_COMMAND = "DETAILS";

    /**
     * Command to process the user inputs and to return the intended command with the correct parameters.
     *
     * @param userInput input of user
     * @param allModules data of all modules offered by NUS
     * @param currentPerson person using the app
     * @param ui ui for output
     * @return Command to be executed
     * @throws CommandParserException to return with error message
     */
    public static Command parse(String userInput, ModuleLoader allModules, Person currentPerson, Ui ui)
        throws CommandParserException {
        String[] inputs = userInput.toUpperCase().split(" ");
        Scanner in = ui.getScanner();

        if (inputs[COMMAND_INDEX].equals(ADD_COMMAND) && inputs.length == CORRECT_COMMAND_LENGTH) {
            return new AddModuleCommand(allModules, currentPerson, in, inputs[MODULE_CODE_INDEX]);
        } else if (inputs[COMMAND_INDEX].equals(EDIT_COMMAND) && inputs.length == CORRECT_COMMAND_LENGTH) {
            return new EditModuleCommand(allModules, currentPerson, in, inputs[MODULE_CODE_INDEX]);
        } else if (inputs[COMMAND_INDEX].equals(REMOVE_COMMAND) && inputs.length == CORRECT_COMMAND_LENGTH) {
            return new RemoveModuleCommand(allModules, currentPerson, in, inputs[MODULE_CODE_INDEX]);
        } else if (inputs[COMMAND_INDEX].equals(VIEW_COMMAND)) {
            return new PrintCalenderCommand(currentPerson, in);
        } else if (inputs[COMMAND_INDEX].equals(HELP_COMMAND)) {
            return new PrintHelpCommand();
        } else if (inputs[COMMAND_INDEX].equals(DETAILS_COMMAND)) {
            return new ModuleDetailsCommand(allModules, inputs[MODULE_CODE_INDEX]);
        } else if (inputs[COMMAND_INDEX].equals(SEARCH_COMMAND)) {
            return new SearchModuleCommand(allModules, inputs[MODULE_CODE_INDEX]);
        } else if (inputs[COMMAND_INDEX].equals(EXIT_COMMAND)) {
            return new Command(true);
        } else {
            throw new CommandParserException(ERROR_INVALID_COMMAND + NEW_LINE + EXITING_CURRENT_COMMAND);
        }
    }
}
