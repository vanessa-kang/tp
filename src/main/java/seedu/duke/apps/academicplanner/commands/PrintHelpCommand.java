package seedu.duke.apps.academicplanner.commands;

import seedu.duke.globalcommons.Command;

/**
 * Class representing a print help command from the academic planner.
 */
public class PrintHelpCommand extends Command {

    private static final String COMMANDS_LIST = "Available commands are:\n"
            + "\tadd <module code>\n"
            + "\tedit <module code>\n"
            + "\tremove <module code>\n"
            + "\tview\n"
            + "\thelp\n"
            + "\texit";

    public PrintHelpCommand() {
        //Constructor is intentionally left blank
    }

    /**
     * Prints commands list for academic planner.
     */
    public void execute() {
        System.out.println(COMMANDS_LIST);
    }
}
