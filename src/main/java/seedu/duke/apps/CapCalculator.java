package seedu.duke.apps;

import seedu.duke.exceptions.InvalidCapException;
import seedu.duke.exceptions.InvalidCreditException;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CapCalculator extends App {
    private Person person;
    private Scanner scanner;
    private Ui ui;
    private final DecimalFormat formatFinalCap = new DecimalFormat("#.##");

    //CONSTANTS
    private static final double MAXIMUM_CAP = 5.00;
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private static final String AWAIT_COMMAND = "Type a command to continue...";
    private static final String EXIT_COMMAND = "EXIT";
    private static final String CURRENT_COMMAND = "CURRENT";
    private static final String SET_TARGET_COMMAND = "SET TARGET";
    private static final String EXIT_MESSAGE = "EXITING CAPCALC";
    private static final String INVALID_CAP_MESSAGE = "Your target CAP cannot be greater than the maximum CAP of 5!";
    private static final String INVALID_MC_MESSAGE = "Your target MC cannot be 0!";
    private static final String WELCOME_MESSAGE = "Welcome to CAP Calculator! Commands available are:\n"
            + "  Current\n"
            + "  Set target\n"
            + "To exit CAP Calculator, use command: \"exit\"\n\n"
            + "Initializing your CAP...";

    public CapCalculator(Person currentPerson, Ui ui) {
        this.person = currentPerson;
        this.ui = ui;
        this.scanner = ui.getScanner();
    }

    //Main Function
    public void run() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(AWAIT_COMMAND);
        String input = scanner.nextLine().toUpperCase();
        formatFinalCap.setRoundingMode(RoundingMode.UP);

        while (!input.equals(EXIT_COMMAND)) {
            if (input.equals(CURRENT_COMMAND)) {
                printCurrentCap();
            } else if (input.equals(SET_TARGET_COMMAND)) {
                setTargetCap();
            } else {
                System.out.println(ERROR_INVALID_COMMAND);
            }
            input = scanner.nextLine().toUpperCase();
        }
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Prints out current CAP and number of graded MCs.
     */
    private void printCurrentCap() {
        double currentCap = person.getCurrentTotalMcxGrade() / (double) person.getCurrentMcAfterSU();
        System.out.println("Your current now CAP is: " + formatCapToString(currentCap));
        System.out.println("Number of graded MCs taken is: " + person.getCurrentMcAfterSU());
    }

    /**
     * Allow the user to set the target CAP that user want to achieve in the next given MCs.
     */
    private void setTargetCap() {
        try {
            System.out.println("What is your target CAP?");
            double targetCap = Double.parseDouble(scanner.nextLine());
            checkValidCap(targetCap);

            System.out.println("How many graded MCs you are taking to achieve the target CAP?");
            int targetGradedMC = Integer.parseInt(scanner.nextLine());
            checkValidCredits(targetGradedMC);

            calculateResults(targetCap, targetGradedMC);
        } catch (NullPointerException e) {
            System.out.println(ERROR_INVALID_COMMAND);
            System.out.println(AWAIT_COMMAND);
        } catch (InvalidCapException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCreditException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the target Cap given by the user is valid.
     *
     * @param cap Cap to be checked
     * @throws InvalidCapException if the Cap given is greater than 5.00
     */
    private void checkValidCap(double cap) throws InvalidCapException {
        if (cap > MAXIMUM_CAP) {
            throw new InvalidCapException(INVALID_CAP_MESSAGE);
        }
    }

    /**
     * Checks if the target Cap given by the user is valid.
     *
     * @param credits Module credits to be checked
     * @throws InvalidCreditException if the module credit given is 0
     */
    private void checkValidCredits(int credits) throws InvalidCreditException {
        if (credits == 0) {
            throw new InvalidCreditException(INVALID_MC_MESSAGE);
        }
    }

    /**
     * Calculate what should be the user's minimum CAP in order to achieve user's target CAP.
     */
    private void calculateResults(double targetCap,int targetGradedMC) {
        int totalMcToTarget = person.getCurrentMcAfterSU() + targetGradedMC;
        double targetCapxTargetMC = (double) totalMcToTarget * targetCap;
        double neededCap = (targetCapxTargetMC - person.getCurrentTotalMcxGrade()) / (double) targetGradedMC;

        if (neededCap <= 5) {
            System.out.println("You should achieve a minimum CAP of " + formatCapToString(neededCap) + " for your next "
                    + targetGradedMC + " MCs to achieve your target CAP of " + targetCap + ".");
        } else {
            System.out.println("OOPS!! Looks like you are not able to achieve your target CAP of " + targetCap
                    + " with you target MCs of " + targetGradedMC + ".");
        }
    }

    /**
     * Returns CAP score as a string.
     *
     * @param academicPoint academic point to parse
     * @return string of academic point
     */
    private String formatCapToString(double academicPoint) {
        if (isNaN(academicPoint)) {
            return "0";
        }
        return formatFinalCap.format(academicPoint);
    }

    /**
     * Returns true if CAP is NaN
     * else returns false.
     *
     * @param academicPoint academic point to check
     * @return boolean
     */
    public boolean isNaN(double academicPoint) {
        return (academicPoint != academicPoint);
    }
}
