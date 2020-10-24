package seedu.duke.apps.capcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.capcalculator.commands.SetSuByModuleCommand;
import seedu.duke.apps.capcalculator.commands.SetSuBySemesterCommand;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class SetSuParserTest {
    private static final String INVALID_SU_ERROR = "Number entered does not correspond to any S/U method.";
    private Person currentPerson;
    private Scanner in;

    @BeforeEach
    public void setup() {
        currentPerson = new Person("Bob");
        in = new Ui().getScanner();
    }

    @Test
    void parse_SetSuByModuleCommand_parsedCorrectly() {
        final String choice = "2";
        parseAndAssertCommandType(choice, SetSuByModuleCommand.class, currentPerson, in);
    }

    @Test
    void parse_SetSuBySemesterCommand_parsedCorrectly() {
        final String choice = "1";
        parseAndAssertCommandType(choice, SetSuBySemesterCommand.class, currentPerson, in);
    }

    @Test
    void parse_unknownChoice_exceptionThrown() {
        try {
            final String unknownChoice = "unknown choice";
            SetSuParser.parse(unknownChoice, currentPerson, in);
        } catch (Exception e) {
            assertEquals(INVALID_SU_ERROR, e.getMessage());
        }
    }

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param choice to be parsed
     * @param expectedCommandClass expected class of returned command
     */
    private <T extends Command> void parseAndAssertCommandType(String choice, Class<T> expectedCommandClass,
                                                               Person currentPerson, Scanner in) {
        try {
            final Command result = SetSuParser.parse(choice, currentPerson, in);
            assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        } catch (Exception e) {
            fail();
        }
    }
}