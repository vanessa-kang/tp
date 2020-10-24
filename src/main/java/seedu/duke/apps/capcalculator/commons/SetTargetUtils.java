package seedu.duke.apps.capcalculator.commons;

import seedu.duke.apps.capcalculator.exceptions.InvalidCapException;
import seedu.duke.apps.capcalculator.exceptions.InvalidCreditException;
import seedu.duke.global.objects.Person;

import java.util.Scanner;

import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.MAXIMUM_CAP;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.formatCapToString;

//@@author JuZihao
/**
 * Class representing the common Set Target functions in CAP Calculator.
 */
public class SetTargetUtils {
    private static final String INVALID_CAP_MESSAGE = "Your target CAP cannot be greater than the maximum CAP of 5!";
    private static final String INVALID_MC_MESSAGE = "Your target MC cannot be 0!";

    private Person currentPerson;
    private Scanner in;

    public SetTargetUtils(Person currentPerson, Scanner in) {
        this.currentPerson = currentPerson;
        this.in = in;
    }

    /**
     * Returns the target Cap obtained from the user.
     *
     * @throws InvalidCapException if the Cap given is greater than 5.00 or less than 0
     */
    public double getTargetCap() throws InvalidCapException {
        System.out.println("What is your target CAP?");
        double targetCap = Double.parseDouble(in.nextLine());
        if (isValidCap(targetCap)) {
            return targetCap;
        } else {
            throw new InvalidCapException(INVALID_CAP_MESSAGE);
        }
    }

    /**
     * Returns the target MCs obtained from the user.
     *
     * @throws InvalidCreditException if the module credit given less than 0
     */
    public int getTargetGradedMC() throws InvalidCreditException {
        System.out.println("How many graded MCs you are taking to achieve the target CAP?");
        int targetGradedMC = Integer.parseInt(in.nextLine());
        if (isValidCredits(targetGradedMC)) { 
            return targetGradedMC;
        } else {
            throw new InvalidCreditException(INVALID_MC_MESSAGE);
        }
    }

    /**
     * Checks if the target Cap given by the user is valid.
     * Returns true when the Cap provided is less than 5.00 and more or equals to 0.
     * Returns false otherwise.
     *
     * @param cap Cap to be checked
     * @return boolean whether Cap is valid
     */
    private boolean isValidCap(double cap) {
        return cap <= MAXIMUM_CAP && cap >= 0;
    }

    /**
     * Checks if the target Cap given by the user is valid.
     * Returns false when the MC provided is less than 0 else false.
     *
     *  @param credits Module credits to be checked
     *  @return boolean whether MC is valid
     */
    private boolean isValidCredits(int credits) {
        return credits > 0;
    }

    /**
     * Calculate what should be the user's minimum CAP in order to achieve user's target CAP and display to user.
     */
    public void showResultsToUser(double targetCap,int targetGradedMC) {
        int totalMcToTarget = currentPerson.getCurrentMcAfterSU() + targetGradedMC;
        double targetCapxTargetMC = (double) totalMcToTarget * targetCap;
        double neededCap = (targetCapxTargetMC - currentPerson.getCurrentTotalMcxGrade()) / (double) targetGradedMC;

        if (isValidCap(neededCap)) {
            printTargetResultPossible(targetCap, targetGradedMC, neededCap);
        } else {
            printTargetResultImpossible(targetCap, targetGradedMC);
        }
    }

    /**
     * Prints out message for user when the target result cannot be achieved.
     *
     * @param targetCap user's targeted CAP.
     * @param targetGradedMC user's targeted MC.
     */
    private void printTargetResultImpossible(double targetCap, int targetGradedMC) {
        System.out.println("OOPS!! Looks like you are not able to achieve your target CAP of " + targetCap
                + " with you target MCs of " + targetGradedMC + ".");
    }

    /**
     * Prints out message for user when the target result can be achieved.
     *
     * @param targetCap user's targeted CAP.
     * @param targetGradedMC user's targeted MC.
     * @param neededCap user's future CAP needed to reach targeted CAP
     */
    private void printTargetResultPossible(double targetCap, int targetGradedMC, double neededCap) {
        System.out.println("You should achieve a minimum CAP of " + formatCapToString(neededCap) + " for your next "
                + targetGradedMC + " MCs to achieve your target CAP of " + targetCap + ".");
    }
}
