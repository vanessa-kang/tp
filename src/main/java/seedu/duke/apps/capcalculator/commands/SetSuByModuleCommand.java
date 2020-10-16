package seedu.duke.apps.capcalculator.commands;

import seedu.duke.apps.capcalculator.commons.SetSuUtils;
import seedu.duke.apps.capcalculator.exceptions.CapCalculatorException;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing the set S/U by modules command for CAP Calculator.
 */
public class SetSuByModuleCommand extends Command {

    private SetSuUtils setSuUtils;
    private ArrayList<PartialModule> suList;

    public SetSuByModuleCommand(Person currentPerson, Scanner in) {
        this.setSuUtils = new SetSuUtils(currentPerson, in);
    }

    /**
     * Function to calculate the best CAP after S/Uing some specific modules.
     */
    @Override
    public void execute() {
        try {
            int numberOfModulesToSu = setSuUtils.promptUserForNumberOfModules();
            suList = setSuUtils.getSuListByModule(numberOfModulesToSu);
            setSuUtils.showSuListToUser(suList);
            setSuUtils.showResultsToUser(suList);
        } catch (CapCalculatorException e) {
            System.out.println(e.getMessage());
        }
    }
}
