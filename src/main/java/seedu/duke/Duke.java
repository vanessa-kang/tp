package seedu.duke;

import seedu.duke.apps.ModuleInitializer;
import seedu.duke.globalcommons.App;
import seedu.duke.objects.Person;
import seedu.duke.parser.AppParser;
import seedu.duke.ui.Ui;

/**
 * Class representing main function of PlanNUS.
 */
public class Duke {

    private static final String WELCOME_MESSAGE = "Welcome to PlanNUS!";
    private static final String WELCOME_BACK_MESSAGE = "Welcome back to PlanNUS Main Menu!";
    private static final String EXIT_MESSAGE = "Thanks for using PlanNUS! We hope to see you again!";
    private static final String HELP_MESSAGE = "\tFor academic planner, type <acadplan>\n"
            + "\tFor CAP calculator, type <capcalc>\n"
            + "\tTo exit PlanNUS, type <exit>";
    private Ui ui;
    private ModuleInitializer allModules;
    private Person currentPerson;

    public Duke() {
        ui = new Ui();
        allModules =  new ModuleInitializer();
        currentPerson = new Person("Bob");
    }

    /**
     * Main entry function for PlanNUS.
     */
    public void run() {
        showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.getScanner().nextLine();
                App selectedApp = AppParser.parse(userInput, allModules, currentPerson, ui);
                selectedApp.run();
                isExit = selectedApp.getIsExit();
                if (!isExit) {
                    showWelcomeBackMessage();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        showExitMessage();
    }

    /**
     * Prints exit message for user just before termination of program.
     */
    private void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Prints welcome back message for user when user returns back to main menu.
     */
    private void showWelcomeBackMessage() {
        System.out.println(WELCOME_BACK_MESSAGE);
        System.out.println(HELP_MESSAGE);
    }

    /**
     * Prints welcome message for user upon first entry into PlanNUS.
     */
    private void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(HELP_MESSAGE);
    }

    /**
     * Main executable code.
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
