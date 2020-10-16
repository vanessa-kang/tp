package seedu.duke.apps.capcalculator.commands;

import seedu.duke.apps.capcalculator.exceptions.InvalidCapException;
import seedu.duke.apps.capcalculator.exceptions.InvalidCreditException;
import seedu.duke.globalcommons.Command;
import seedu.duke.globalcommons.LoggingTool;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;
import seedu.duke.apps.capcalculator.commons.SetTargetUtils;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing the Set target CAP command for CAP Calculator.
 */
public class SetTargetCommand extends Command {
    private static final double MAXIMUM_CAP = 5.00;
    private static final String LOG_FILE_NAME = "SetTargetCommand.log";
    private static final String LOGGER_NAME = "SetTargetCommand";

    private static Logger logger;
    private static FileHandler fh;
    private Person currentPerson;
    private Ui ui;
    private SetTargetUtils setTargetUtils;

    public SetTargetCommand(Person currentPerson, Ui ui) {
        this.currentPerson = currentPerson;
        this.ui = ui;
        this.setTargetUtils = new SetTargetUtils(currentPerson, ui);
    }

    /**
     * Function to calculate CAP needed to obtain target grades.
     */
    @Override
    public void execute() {
        try {
            fh = new FileHandler(LOG_FILE_NAME);
            logger = new LoggingTool(LOGGER_NAME,fh).initialize();
            logger.log(Level.INFO,"Executing set target command.");
            double targetCap = setTargetUtils.getTargetCap();
            int targetMCs = setTargetUtils.getTargetGradedMC();
            assert targetCap <= MAXIMUM_CAP;
            assert targetMCs > 0;
            setTargetUtils.showResultsToUser(targetCap, targetMCs);
            logger.log(Level.INFO,"Finish executing set target command.");
            fh.close();
        } catch (InvalidCapException e) {
            logger.log(Level.WARNING,"Cap entered is more than 5.00.");
            fh.close();
            System.out.println(e.getMessage());
        } catch (InvalidCreditException e) {
            logger.log(Level.WARNING,"MC entered is less than 0.");
            fh.close();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
