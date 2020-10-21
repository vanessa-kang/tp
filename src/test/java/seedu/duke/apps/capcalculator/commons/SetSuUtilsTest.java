package seedu.duke.apps.capcalculator.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.capcalculator.exceptions.CapCalculatorException;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.apps.moduleloader.exceptions.ModuleLoaderException;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;

import java.util.ArrayList;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SetSuUtilsTest {
    private static final String EMPTY_LIST_ERROR = "Looks like your S/U list is empty!";
    private static final String INVALID_MODULE_ERROR = "Looks like the module you entered is not a valid module!";
    private static final String EXITING_CURRENT_COMMAND =
            "Exiting current command back to CAP Calculator App Main Menu.";
    private static final String NEW_LINE = "\n";

    Person currentPerson;
    AddUtils addUtils;
    SetSuUtils setSuUtils;
    ModuleLoader allModules;
    Scanner in;
    ArrayList<PartialModule> suList;
    PartialModule suModule;
    ArrayList<PartialModule> filteredList;

    @BeforeEach
    void setup() {
        try {
            currentPerson = new Person("Bobby");
            allModules = new ModuleLoader();
            addUtils = new AddUtils(allModules,currentPerson);

            addUtils.addModuleToUser("CS1010",1,"A-",4);
            addUtils.addModuleToUser("CS1231",1,"B",4);
            addUtils.addModuleToUser("CG1111",1,"B+",6);
            addUtils.addModuleToUser("MA1511",1,"S",4);
            addUtils.addModuleToUser("MA1512",1,"S",4);

            addUtils.addModuleToUser("CS2040C",2,"A-",4);
            addUtils.addModuleToUser("CG1112",2,"B",4);
            addUtils.addModuleToUser("MA1508E",2,"S",4);
            addUtils.addModuleToUser("GER1000",2,"A",4);
            addUtils.addModuleToUser("GET1002",2,"B+",4);

            setSuUtils = new SetSuUtils(currentPerson, in);
            filteredList = (ArrayList<PartialModule>) currentPerson.getModulesList().stream()
                    .filter((partialModule) -> partialModule.getCap() >= 0)
                    .collect(toList());

        } catch (ModuleLoaderException e) {
            e.printStackTrace();
        }
    }

    @Test
    void execute_getSuListBySemester_result() {
        try {
            suList = setSuUtils.getSuListBySemester(1);
            assertEquals(suList.size(),3);

            suList = setSuUtils.getSuListBySemester(2);
            assertEquals(suList.size(),4);
        } catch (CapCalculatorException e) {
            fail();
        }
    }

    @Test
    void execute_getSuListBySemester_exceptionThrown() {
        try {
            suList = setSuUtils.getSuListBySemester(4);
            assertEquals(suList.size(),0);
        } catch (CapCalculatorException e) {
            assertEquals(e.getMessage(),EMPTY_LIST_ERROR + NEW_LINE + EXITING_CURRENT_COMMAND);
        }
    }

    @Test
    void execute_getSuModule_result() {
        try {
            suModule = setSuUtils.getSuModule(filteredList, "CS1010");
            assertEquals(suModule.getModuleCode(),"CS1010");
            assertEquals(suModule.getCap(),4.50);
            assertEquals(suModule.getModuleCredit(),4);

            suModule = setSuUtils.getSuModule(filteredList, "CS1231");
            assertEquals(suModule.getModuleCode(),"CS1231");
            assertEquals(suModule.getCap(),3.50);
            assertEquals(suModule.getModuleCredit(),4);
        } catch (CapCalculatorException e) {
            fail();
        }
    }

    @Test
    void execute_getSuModule_exceptionThrown() {
        try {
            suModule = setSuUtils.getSuModule(filteredList, "MA1511");
        } catch (CapCalculatorException e) {
            assertEquals(e.getMessage(),INVALID_MODULE_ERROR + NEW_LINE + EXITING_CURRENT_COMMAND);
        }
    }
}