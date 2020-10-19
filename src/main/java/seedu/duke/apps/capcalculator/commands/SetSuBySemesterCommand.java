package seedu.duke.apps.capcalculator.commands;

import seedu.duke.apps.capcalculator.commons.SetSuUtils;
import seedu.duke.apps.capcalculator.exceptions.CapCalculatorException;
import seedu.duke.global.Command;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing the set S/U by semester command for CAP Calculator.
 */
public class SetSuBySemesterCommand extends Command {

    private SetSuUtils setSuUtils;
    private ArrayList<PartialModule> suList;

    public SetSuBySemesterCommand(Person currentPerson, Scanner in) {
        this.setSuUtils = new SetSuUtils(currentPerson, in);
    }

    /**
     * Function to calculate the best CAP after S/Uing a specific semester.
     */
    @Override
    public void execute() {
        try {
            int semesterToSu = setSuUtils.promptUserForSemester();
            suList = setSuUtils.getSuListBySemester(semesterToSu);
            setSuUtils.showSuListToUser(suList);
            setSuUtils.showResultsToUser(suList);
        } catch (CapCalculatorException e) {
            System.out.println(e.getMessage());
        }
    }
}
