package seedu.duke.parser;

import seedu.duke.commands.AcademicPlannerAddCommand;
import seedu.duke.commands.AcademicPlannerCommand;
import seedu.duke.apps.ModuleInitializer;
import seedu.duke.exceptions.AcademicPlannerParserException;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;


public class AcademicPlannerParser {

    private static final String ADD_COMMAND = "add";
    private static final String EDIT_COMMAND = "edit;";
    private static final String REMOVE_COMMAND = "remove";
    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "exit";
    private static final String INVALID_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static AcademicPlannerCommand parse(String userInput, ModuleInitializer allmodules,
                                               Person currentPerson, Ui ui) throws AcademicPlannerParserException {
        userInput = userInput.trim().toLowerCase();

        if (userInput.startsWith(ADD_COMMAND)) {
            //ADD COMMAND
            System.out.println("add command");
            return new AcademicPlannerAddCommand(userInput, allmodules, currentPerson, ui);
        }  else if (userInput.startsWith(EDIT_COMMAND)) {
            //EDIT COMMAND
            System.out.println("edit command");
            return new AcademicPlannerAddCommand(userInput, allmodules, currentPerson, ui);
        } else if (userInput.startsWith(REMOVE_COMMAND)) {
            //REMOVE COMMAND
            System.out.println("remove command");
            return new AcademicPlannerAddCommand(userInput, allmodules, currentPerson, ui);
        } else if (userInput.equals(LIST_COMMAND)) {
            //LIST COMMAND
            System.out.println("list command");
            return new AcademicPlannerAddCommand(userInput, allmodules, currentPerson, ui);
        } else if (userInput.equals(EXIT_COMMAND)) {
            //EXIT
            return new AcademicPlannerCommand(true);
        } else {
            throw new AcademicPlannerParserException(INVALID_COMMAND_MESSAGE);
        }
    }
}
