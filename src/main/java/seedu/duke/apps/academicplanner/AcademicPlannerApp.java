package seedu.duke.apps.academicplanner;

import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.App;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

/**
 * Class representing the Academic Planner Application.
 */
public class AcademicPlannerApp extends App {
    private static final String WELCOME_MESSAGE = "\nWelcome to Academic Planner!";
    private static final String EXIT_MESSAGE = "Thank you for using Academic Planner!";
    private static final String AWAIT_COMMAND = "Type in a command to continue...";
    private static final String COMMANDS_LIST = "Available commands are:\n"
            + "\tadd <module code>\n"
            + "\tedit <module code>\n"
            + "\tremove <module code>\n"
            + "\tdetails <module code>\n"
            + "\tview\n"
            + "\thelp\n"
            + "\texit";

    private final ModuleLoader allModules;
    private final Person currentPerson;
    private Ui ui;

    /**
     * Default constructor for Academic Planner App.
     *
     * @param allModules all modules offered by NUS
     * @param currentPerson current user
     * @param ui ui of PlanNUS
     */
    public AcademicPlannerApp(ModuleLoader allModules, Person currentPerson, Ui ui) {
        this.allModules = allModules;
        this.currentPerson = currentPerson;
        this.ui = ui;
    }

    /**
     * Main entry point for the Academic Planner Application.
     */
    public void run() {
        showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                System.out.println(AWAIT_COMMAND);
                String userInput = ui.getScanner().nextLine();
                Command commandInput = AcademicPlannerParser.parse(userInput, allModules, currentPerson, ui);
                commandInput.execute();
                isExit = commandInput.getIsExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        showExitMessage();
    }

    /**
     * Prints welcome message for Academic Planner.
     */
    private void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(COMMANDS_LIST);
    }

    /**
     * Prints exit message for Academic Planner.
     */
    private void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }
}
