package seedu.duke.apps.capcalculator.commands;

import seedu.duke.apps.capcalculator.exceptions.InvalidCapException;
import seedu.duke.apps.capcalculator.exceptions.InvalidCreditException;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;
import seedu.duke.apps.capcalculator.commons.SetTargetUtils;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing the Set target CAP command for CAP Calculator.
 */
public class SetTargetCommand extends Command {
    private static final double MAXIMUM_CAP = 5.00;

    private static final Logger logger = Logger.getLogger("SetTargetCommand.java");
    private SetTargetUtils setTargetUtils;

    public SetTargetCommand(Person currentPerson, Scanner in) {
        this.setTargetUtils = new SetTargetUtils(currentPerson, in);
    }

    /**
     * Function to calculate CAP needed to obtain target grades.
     */
    @Override
    public void execute() {
        try {
            logger.setLevel(Level.WARNING);
            logger.log(Level.INFO,"Executing set target command.");
            double targetCap = setTargetUtils.getTargetCap();
            int targetMCs = setTargetUtils.getTargetGradedMC();
            assert targetCap <= MAXIMUM_CAP;
            assert targetMCs > 0;
            setTargetUtils.showResultsToUser(targetCap, targetMCs);
            logger.log(Level.INFO,"Finish executing set target command.");
        } catch (InvalidCapException e) {
            logger.log(Level.WARNING,"Cap entered is more than 5.00.");
            System.out.println(e.getMessage());
        } catch (InvalidCreditException e) {
            logger.log(Level.WARNING,"MC entered is less than 0.");
            System.out.println(e.getMessage());
        }
    }
}
