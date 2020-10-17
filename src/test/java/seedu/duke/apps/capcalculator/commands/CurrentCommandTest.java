package seedu.duke.apps.capcalculator.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.academicplanner.commons.RemoveUtils;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CurrentCommandTest {
    Person currentPerson;
    ModuleLoader allModules;
    AddUtils addUtils;
    RemoveUtils removeUtils;

    @BeforeEach
    void setup() throws ModuleLoaderException {
        currentPerson = new Person("Bob");
        allModules = new ModuleLoader();
        addUtils = new AddUtils(allModules,currentPerson);
        removeUtils = new RemoveUtils(currentPerson);
    }

    @Test
    void execute_populatedList_result() {
        addUtils.addModuleToUser("CS1010",1,"A-",4);
        addUtils.addModuleToUser("CS1231",1,"B",4);
        addUtils.addModuleToUser("CG1111",1,"B+",6);
        addUtils.addModuleToUser("MA1511",1,"S",4);
        addUtils.addModuleToUser("MA1512",1,"S",4);

        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        assertEquals(currentCap,4.0);
    }

    @Test
    void execute_populatedListAllSU_fail() {
        addUtils.addModuleToUser("CS1010",1,"S",4);
        addUtils.addModuleToUser("CS1231",1,"S",4);
        addUtils.addModuleToUser("CG1111",1,"S",6);
        addUtils.addModuleToUser("MA1511",1,"S",4);
        addUtils.addModuleToUser("MA1512",1,"S",4);

        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        boolean isNan = Double.isNaN(currentCap);
        assertTrue(isNan);
    }

    @Test
    void execute_emptyList_fail() {
        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        boolean isNan = Double.isNaN(currentCap);
        assertTrue(isNan);
    }

    @Test
    void execute_addOneRemoveOne_fail() {
        addUtils.addModuleToUser("CS1010",1,"S",4);
        removeUtils.removeModuleFromUserModuleList("CS1010");
        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        boolean isNan = Double.isNaN(currentCap);
        assertTrue(isNan);
    }

    @Test
    void execute_addTwoRemoveOne_result() {
        addUtils.addModuleToUser("CS1010",1,"S",4);
        addUtils.addModuleToUser("CS2040",1,"A",4);
        removeUtils.removeModuleFromUserModuleList("CS1010");
        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        assertEquals(currentCap,5.0);
    }

    @Test
    void execute_addTwoRemoveOne_fail() {
        addUtils.addModuleToUser("CS1010",1,"A",4);
        addUtils.addModuleToUser("CS2040",1,"S",4);
        removeUtils.removeModuleFromUserModuleList("CS1010");
        CurrentCommand currentCommand = new CurrentCommand(currentPerson);
        double currentCap = currentCommand.getCurrentCap();
        boolean isNan = Double.isNaN(currentCap);
        assertTrue(isNan);
    }
}