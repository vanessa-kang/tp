package seedu.duke.apps;

import seedu.duke.objects.Person;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CapCalculator {
    private final Person person;
    private final DecimalFormat formatFinalCAP = new DecimalFormat("#.##");

    //CONSTANTS
    private final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    private final String AWAIT_COMMAND = "Type a command to continue...";
    private final String EXIT_COMMAND = "EXIT";
    private final String CURRENT_COMMAND = "CURRENT";
    private final String SET_TARGET_COMMAND = "SET TARGET";
    private final String EXIT_MESSAGE = "EXITING CAPCALC";
    private final String WELCOME_MESSAGE = "Welcome to CAP Calculator! Commands available are:\n"
            + "  Current\n"
            + "  Set target\n"
            + "To exit CAP Calculator, use command: \"exit\"\n\n"
            + "Initializing your CAP...";

    public CapCalculator(Person currentPerson) {
        this.person = currentPerson;
    }

    //Main Function
    public void CAPCalculator() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(AWAIT_COMMAND);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();
        formatFinalCAP.setRoundingMode(RoundingMode.UP);

        while (!input.equals(EXIT_COMMAND)) {
            if (input.equals(CURRENT_COMMAND)) {
                printCurrentCAP();
            } else if (input.equals(SET_TARGET_COMMAND)) {
                setTargetCAP();
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
    private void printCurrentCAP() {
        double currentCAP = person.getCurrentTotalMcxGrade() / (double) person.getCurrentMcAfterSU();
        System.out.println("Your current now CAP is: " + formatCAPToString(currentCAP));
        System.out.println("Number of graded MCs taken is: " + person.getCurrentMcAfterSU());
    }

    /**
     * Allow the user to set the target CAP that user want to achieve in the next given MCs.
     */
    private void setTargetCAP() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("What is your target CAP?");
            double targetCAP = Double.parseDouble(in.nextLine());

            System.out.println("How many graded MCs you are taking to achieve the target CAP?");
            int targetGradedMC = Integer.parseInt(in.nextLine());

            calculateResults(targetCAP, targetGradedMC);
        } catch (NullPointerException e) {
            System.out.println(ERROR_INVALID_COMMAND);
            System.out.println(AWAIT_COMMAND);
        }
    }

    /**
     * Calculate what should be the user's minimum CAP in order to achieve user's target CAP.
     */
    private void calculateResults(double targetCAP,int targetGradedMC) {
        int totalMCToTarget = person.getCurrentMcAfterSU() + targetGradedMC;
        double targetCAPxTargetMC = (double) totalMCToTarget * targetCAP;
        double neededCAP = (targetCAPxTargetMC - person.getCurrentTotalMcxGrade()) / (double) targetGradedMC;

        if (neededCAP <= 5) {
            System.out.println("You should achieve a minimum CAP of " + formatCAPToString(neededCAP) + " for your next "
                    + targetGradedMC + " MCs to achieve your target CAP of " + targetCAP + ".");
        } else {
            System.out.println("OOPS!! Looks like you are not able to achieve your target CAP of " + targetCAP
                    + " with you target MCs of " + targetGradedMC + ".");
        }
    }

    /**
     * Returns CAP score as a string.
     *
     * @param academicPoint academic point to parse
     * @return string of academic point
     */
    private String formatCAPToString(double academicPoint) {
        if (isNaN(academicPoint)) {
            return "0";
        }
        return formatFinalCAP.format(academicPoint);
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
