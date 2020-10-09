package seedu.duke.apps.capcalculator.commands;

import seedu.duke.exceptions.InvalidCapException;
import seedu.duke.exceptions.InvalidCreditException;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;
import seedu.duke.apps.capcalculator.commons.SetTargetUtils;


public class SetTargetCommand extends Command {

    private Person currentPerson;
    private Ui ui;
    private SetTargetUtils setTargetUtils;

    public SetTargetCommand(Person currentPerson, Ui ui) {
        this.currentPerson = currentPerson;
        this.ui = ui;
        this.setTargetUtils = new SetTargetUtils(currentPerson, ui);
    }

    @Override
    public void execute() {
        try {
            double targetCap = setTargetUtils.getTargetCap();
            int targetMCs = setTargetUtils.getTargetGradedMC();
            setTargetUtils.showResultsToUser(targetCap, targetMCs);
        } catch (InvalidCapException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCreditException e) {
            System.out.println(e.getMessage());
        }
    }
}
