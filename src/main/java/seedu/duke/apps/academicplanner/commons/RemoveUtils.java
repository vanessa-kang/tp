package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class representing remove module utilities from the remove module command.
 */
public class RemoveUtils {
    private static final int FROM_REMOVE = 3;

    private final Map<String, Integer> modulesAddedMap;
    private final ArrayList<PartialModule> modulesList;
    private final CalculatorUtils calculatorUtils;
    private final Person currentPerson;

    /**
     * Default constructor for RemoveUtils.
     *
     * @param currentPerson current user
     */
    public RemoveUtils(Person currentPerson) {
        this.modulesList = currentPerson.getModulesList();
        this.calculatorUtils = new CalculatorUtils(currentPerson);
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
        this.currentPerson = currentPerson;
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
        depopulate(module);

        assert modulesList.size() == totalNumberOfModules - 1;
    }

    /**
     * Removes module from both arraylist and hashmap of the user.
     *
     * @param module module object to remove
     */
    private void depopulate(PartialModule module) {
        modulesList.remove(module);

        HashMap<String, Integer> newModuleAddedMap = new HashMap<>();
        for(int i = 0; i < modulesList.size(); i++) {
            newModuleAddedMap.put(modulesList.get(i).getModuleCode(), i);
        }

        currentPerson.setModulesAddedMap(newModuleAddedMap);
    }
}
