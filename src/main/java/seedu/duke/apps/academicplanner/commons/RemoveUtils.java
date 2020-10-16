package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
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
        PartialModule item = modulesList.get(moduleIndex);

        System.out.println(item.getModuleCode() + " has been removed from the list");
        calculatorUtils.updateCap(FROM_REMOVE, item);

        modulesList.remove(item);
        modulesAddedMap.remove(moduleCode);

        assert modulesList.size() == totalNumberOfModules - 1;
    }
}
