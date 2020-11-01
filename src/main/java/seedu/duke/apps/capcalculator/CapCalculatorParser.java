package seedu.duke.apps.capcalculator;

import seedu.duke.apps.capcalculator.commands.CurrentCommand;
import seedu.duke.apps.capcalculator.commands.PrintHelpCommand;
import seedu.duke.apps.capcalculator.commands.SetTargetCommand;
import seedu.duke.global.exceptions.CommandParserException;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import java.util.Scanner;

//@@author JuZihao
/**
 * Class representing the parser used in the CAP Calculator app.
 */
public class CapCalculatorParser {
    private static final String PROMPT_SU_CHOICES = "Enter the number corresponding to the method you wish to S/U:\n"
            + "\t1) Semester\n"
            + "\t2) Modules";
    private static final String INVALID_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String CURRENT_COMMAND = "current";
    private static final String SET_TARGET_COMMAND = "set target";
    private static final String SET_SU_COMMAND = "set su";
    private static final String EXIT_COMMAND = "exit";
    private static final String HELP_COMMAND = "help";


    /**
     * Processes user input command and returns command to be executed.
     *
     * @param userInput user input
     * @param currentPerson user of PlanNUS
     * @param ui ui
     * @return Command to be executed
     * @throws CommandParserException thrown when an invalid command is entered
     */
    public static Command parse(String userInput, Person currentPerson, Ui ui) throws CommandParserException {
        Scanner in = ui.getScanner();
        userInput = userInput.replaceAll("\\s+"," ");
        userInput = userInput.trim().toLowerCase();

        if (userInput.equals(CURRENT_COMMAND)) {
            return new CurrentCommand(currentPerson);
        } else if (userInput.equals(SET_TARGET_COMMAND)) {
            return new SetTargetCommand(currentPerson, in);
        } else if (userInput.equals(EXIT_COMMAND)) {
            return new Command(true);
        } else if (userInput.equals((SET_SU_COMMAND))) {
            promptUserForSuCommand();
            String choice = in.nextLine().trim();
            return SetSuParser.parse(choice, currentPerson, ui);
        } else if (userInput.equals(HELP_COMMAND)) {
            return new PrintHelpCommand();
        } else {
            throw new CommandParserException(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Prompt user to enter which S/U command to be parsed.
     */
    private static void promptUserForSuCommand() {
        System.out.println(PROMPT_SU_CHOICES);
    }
}
