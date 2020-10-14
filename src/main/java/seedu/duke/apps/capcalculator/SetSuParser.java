package seedu.duke.apps.capcalculator;

import seedu.duke.apps.capcalculator.commands.SetSuByModuleCommand;
import seedu.duke.apps.capcalculator.commands.SetSuBySemesterCommand;
import seedu.duke.exceptions.CommandParserException;
import seedu.duke.globalcommons.Command;
import seedu.duke.objects.Person;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class SetSuParser {

    private static final String BY_MODULES = "1";
    private static final String BY_SEMESTER = "2";
    private static final String PROMPT_SU_CHOICES = "Enter the number corresponding to the method you wish to S/U:\n"
            + "\t1) Semester\n"
            + "\t2) Modules";
    private static final String INVALID_SU_ERROR = "Number entered does not correspond to any S/U method.";

    public static Command parse(Person currentPerson, Scanner in) throws CommandParserException {
        promptUserForSuCommand();
        String choice = in.nextLine().trim();
        if (choice.equals(BY_SEMESTER)) {
            return new SetSuBySemesterCommand(currentPerson, in);
        } else if (choice.equals(BY_MODULES)) {
            return new SetSuByModuleCommand(currentPerson, in);
        } else {
            throw new CommandParserException(INVALID_SU_ERROR);
        }
    }

    private static void promptUserForSuCommand() {
        System.out.println(PROMPT_SU_CHOICES);
    }
}
