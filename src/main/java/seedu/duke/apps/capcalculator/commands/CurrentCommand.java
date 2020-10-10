package seedu.duke.apps.capcalculator.commands;

import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;

import static seedu.duke.apps.capcalculator.commons.CalculatorUtils.formatCapToString;

public class CurrentCommand extends Command {

    private Person currentPerson;

    public CurrentCommand(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    @Override
    public void execute() {
        double currentCap = currentPerson.getCurrentTotalMcxGrade() / (double) currentPerson.getCurrentMcAfterSU();
        System.out.println("Your current now CAP is: " + formatCapToString(currentCap));
        System.out.println("Number of graded MCs taken is: " + currentPerson.getCurrentMcAfterSU());
    }
}
