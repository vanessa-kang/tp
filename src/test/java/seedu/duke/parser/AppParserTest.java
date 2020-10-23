package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.academicplanner.AcademicPlannerApp;
import seedu.duke.apps.capcalculator.CapCalculatorApp;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.App;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppParserTest {

    private static final String INVALID_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    ModuleLoader allModules;
    Person currentPerson;
    Ui ui;

    @BeforeEach
    void setup() {
        try {
            allModules = new ModuleLoader();
            currentPerson = new Person("Bob");
            ui = new Ui();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parse_emptyInput_exceptionThrown() {
        try {
            final String emptyInput = "";
            AppParser.parse(emptyInput,allModules,currentPerson,ui);
        } catch (Exception e) {
            assertEquals(INVALID_COMMAND_MESSAGE, e.getMessage());
        }
    }

    @Test
    void parse_randomInput_exceptionThrown() {
        try {
            final String randomInput = "i am a gummy bear";
            AppParser.parse(randomInput,allModules,currentPerson,ui);
        } catch (Exception e) {
            assertEquals(INVALID_COMMAND_MESSAGE, e.getMessage());
        }
    }

    @Test
    void parse_acadplan_correct() {
        final String input = "acadplan";
        parseAndAssertCommandType(input, AcademicPlannerApp.class,allModules,currentPerson,ui);
    }

    @Test
    void parse_capcalc_correct() {
        final String input = "capcalc";
        parseAndAssertCommandType(input, CapCalculatorApp.class,allModules,currentPerson,ui);
    }

    private <T extends App> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass,
                                                           ModuleLoader allModules, Person currentPerson, Ui ui) {
        try {
            final App result = AppParser.parse(input, allModules, currentPerson, ui);
            assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        } catch (Exception e) {
            fail();
        }
    }
}