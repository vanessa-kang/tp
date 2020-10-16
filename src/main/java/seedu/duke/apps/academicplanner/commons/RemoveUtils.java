package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class representing remove module utilities from the remove module command.
 */
public class RemoveUtils {
    private static final int FROM_REMOVE = 3;

    private final ArrayList<PartialModule> modulesList;
    private final Map<String, Integer> modulesAddedMap;
    private final CalculatorUtils calculatorUtils;

    /**
     * Default constructor for RemoveUtils.
     *
     * @param currentPerson current user
     */
    public RemoveUtils(Person currentPerson) {
        this.modulesList = currentPerson.getModulesList();
        this.calculatorUtils = new CalculatorUtils(currentPerson);
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
    }

    /**
     * Removes module from user's module list.
     * PartialModule must exist in user's module list.
     *
     * @param moduleCode module to remove.
     */
    public void removeModuleFromUserModuleList(String moduleCode) {
        final int totalNumberOfModules = modulesList.size();

        Integer moduleIndex = modulesAddedMap.get(moduleCode);
        PartialModule module = modulesList.get(moduleIndex);

        calculatorUtils.updateCap(FROM_REMOVE, module);
        depopulate(moduleCode, module);

        assert modulesList.size() == totalNumberOfModules - 1;
    }

    /**
     * Removes module from both arraylist and hashmap of the user.
     *
     * @param moduleCode module code to remove
     * @param module module object to remove
     */
    private void depopulate(String moduleCode, PartialModule module) {
        modulesList.remove(module);
        modulesAddedMap.remove(moduleCode);
    }
}
