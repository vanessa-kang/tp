package seedu.duke.apps.capcalculator.commands;

import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.formatCapToString;

/**
 * Class representing the Current CAP command for CAP Calculator.
 */
public class CurrentCommand extends Command {

    private Person currentPerson;

    public CurrentCommand(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    /**
     * Prints out user's current cap obtained from his/her academic calendar.
     */
    @Override
    public void execute() {
        double currentCap = getCurrentCap();
        System.out.println("Your current now CAP is: " + formatCapToString(currentCap));
        System.out.println("Number of graded MCs taken is: " + currentPerson.getCurrentMcAfterSU());
        System.out.println("Total number of MCs taken is: " + currentPerson.getCurrentMc());
    }

    /**
     * Returns user current cap.
     *
     * @return CAP
     */
    public double getCurrentCap() {
        return currentPerson.getCurrentTotalMcxGrade() / (double) currentPerson.getCurrentMcAfterSU();
    }
}
