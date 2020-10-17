package seedu.duke.ui;

import java.util.Scanner;

/**
 * Class representing the ui for PlanNUS.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to PlanNUS!";
    private static final String WELCOME_BACK_MESSAGE = "Welcome back to PlanNUS Main Menu!";
    private static final String AWAIT_COMMAND = "Type in a command to continue...";
    private static final String EXIT_MESSAGE = "Thanks for using PlanNUS! We hope to see you again!";
    private static final String HELP_MESSAGE = "\tFor academic planner, type <acadplan>\n"
            + "\tFor CAP calculator, type <capcalc>\n"
            + "\tTo exit PlanNUS, type <exit>";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Prints exit message for user just before termination of program.
     */
    public void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Prints welcome back message for user when user returns back to main menu.
     */
    public void showWelcomeBackMessage() {
        System.out.println(WELCOME_BACK_MESSAGE);
        System.out.println(HELP_MESSAGE);
    }

    /**
     * Prints welcome message for user upon first entry into PlanNUS.
     */
    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(HELP_MESSAGE);
    }

    public void showAwaitCommand() {
        System.out.println(AWAIT_COMMAND);
    }

}
