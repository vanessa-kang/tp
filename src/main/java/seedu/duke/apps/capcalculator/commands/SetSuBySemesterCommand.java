package seedu.duke.apps.capcalculator.commands;

import seedu.duke.apps.capcalculator.commons.SetSuUtils;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;

import java.util.Scanner;

/**
 * Class representing the set S/U by semester command for CAP Calculator.
 */
public class SetSuBySemesterCommand extends Command {

    private Person currentPerson;
    private Scanner in;
    private SetSuUtils setSuUtils;

    public SetSuBySemesterCommand(Person currentPerson, Scanner in) {
        this.currentPerson = currentPerson;
        this.in = in;
        this.setSuUtils = new SetSuUtils(currentPerson, in);
    }

    /**
     * Function to calculate the best CAP after S/Uing a specific semester.
     */
    @Override
    public void execute() {

    }
}
