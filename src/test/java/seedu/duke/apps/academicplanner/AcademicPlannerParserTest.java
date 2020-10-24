package seedu.duke.apps.academicplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.academicplanner.commands.AddModuleCommand;
import seedu.duke.apps.academicplanner.commands.EditModuleCommand;
import seedu.duke.apps.academicplanner.commands.ModuleDetailsCommand;
import seedu.duke.apps.academicplanner.commands.PrintCalenderCommand;
import seedu.duke.apps.academicplanner.commands.PrintHelpCommand;
import seedu.duke.apps.academicplanner.commands.RemoveModuleCommand;
import seedu.duke.apps.academicplanner.commands.SearchModulesCommand;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.Command;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class AcademicPlannerParserTest {
    private static final String ERROR_INVALID_COMMAND = "INVALID COMMAND";
    public static final String NEW_LINE = "\n";
    private static final String EXITING_CURRENT_COMMAND = "Exiting current command back to Academic Planner Main Menu.";

    private Person currentPerson;
    private Ui ui;
    private ModuleLoader allModules;

    @BeforeEach
    public void setup() {
        try {
            currentPerson = new Person("Bob");
            ui = new Ui();
            allModules = new ModuleLoader();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void parse_emptyInput_exceptionThrown() {
        try {
            final String emptyInput = "";
            AcademicPlannerParser.parse(emptyInput,allModules,currentPerson,ui);
        } catch (Exception e) {
            assertEquals(ERROR_INVALID_COMMAND + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_randomInput_exceptionThrown() {
        try {
            final String randomInput = "i am a gummy bear";
            AcademicPlannerParser.parse(randomInput,allModules,currentPerson,ui);
        } catch (Exception e) {
            assertEquals(ERROR_INVALID_COMMAND + NEW_LINE + EXITING_CURRENT_COMMAND, e.getMessage());
        }
    }

    @Test
    void parse_addModuleCommand_correct() {
        final String input = "add cs1010";
        parseAndAssertCommandType(input,AddModuleCommand.class,allModules,currentPerson,ui);
    }

    @Test
    void parse_editModuleCommand_correct() {
        final String input = "edit cs1010";
        parseAndAssertCommandType(input, EditModuleCommand.class,allModules,currentPerson,ui);
    }

    @Test
    void parse_moduleDetails_correct() {
        final String input = "details cs1010";
        parseAndAssertCommandType(input,ModuleDetailsCommand.class,allModules,currentPerson,ui);
    }

    @Test
    void parse_printModuleCommand_correct() {
        final String input = "view";
        parseAndAssertCommandType(input, PrintCalenderCommand.class,allModules,currentPerson,ui);
    }

    @Test
    void parse_printHelpCommand_correct() {
        final String input = "help";
        parseAndAssertCommandType(input, PrintHelpCommand.class,allModules,currentPerson,ui);
    }

    @Test
    void parse_removeModuleCommand_correct() {
        final String input = "remove cs1010";
        parseAndAssertCommandType(input, RemoveModuleCommand.class,allModules,currentPerson,ui);
    }

    @Test
    void parse_searchModuleCommand_correct() {
        final String input = "search cs10";
        parseAndAssertCommandType(input,SearchModulesCommand.class,allModules,currentPerson,ui);
    }

    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass,
                                                               ModuleLoader allModules,Person currentPerson, Ui ui) {
        try {
            final Command result = AcademicPlannerParser.parse(input, allModules, currentPerson, ui);
            assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        } catch (Exception e) {
            fail();
        }
    }
}