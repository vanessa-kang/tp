package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.ModuleInitializer;
import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class AddUtils {
    private static final int FROM_ADD = 1;

    private final ModuleInitializer allModules;
    private final ArrayList<PartialModule> modulesList;
    private final HashMap<String, PartialModule> modulesAddedMap;
    private final CalculatorUtils calculatorUtils;

    public AddUtils(ModuleInitializer allModules, Person currentPerson) {
        this.allModules = allModules;
        this.modulesList = currentPerson.getModulesList();
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
        this.calculatorUtils = new CalculatorUtils(currentPerson);
    }

    /**
     * Creates a new instance of a module of the parameters and adds it into the
     * hashmap and array list of user modules.
     *
     * @param moduleCode module to be added
     * @param semesterValue semester that module is taken in
     * @param gradeValue grade achieved for module
     * @param moduleCredit module's credit weightage
     */
    public void addModuleToUser(String moduleCode, int semesterValue, String gradeValue, int moduleCredit) {
        PartialModule newModuleToAdd = new PartialModule(moduleCode, semesterValue, gradeValue, moduleCredit);
        modulesList.add(newModuleToAdd);
        modulesAddedMap.put(moduleCode, newModuleToAdd);
        calculatorUtils.updateCap(FROM_ADD, newModuleToAdd);
        System.out.println(newModuleToAdd.getModuleCode()
                + " added into Semester " + semesterValue + ".");
    }

    /**
     * Function to return module credits for module code.
     *
     * @param moduleCode string of module code
     * @return int of module credit
     */
    public int getModuleCreditForModule(String moduleCode) {
        int mapIndex = allModules.getModuleMap().get(moduleCode);
        return allModules.getModuleFullDetails()[mapIndex].getModuleCredit();
    }
}
