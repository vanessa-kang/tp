package seedu.duke.apps.academicplanner.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static seedu.duke.apps.academicplanner.commons.SharedUtils.getEntryToBeEdited;
import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import seedu.duke.ui.Ui;

//@@author harryleecp
/**
 * Class representing remove module utilities from the remove module command.
 */
public class RemoveUtils {
    private static final int FROM_REMOVE = 3;

    private final Map<String, ArrayList<Integer>> modulesAddedMap;
    private final ArrayList<PartialModule> modulesList;
    private final CalculatorUtils calculatorUtils;
    private final Person currentPerson;
    private final Ui ui;

    /**
     * Default constructor for RemoveUtils.
     *
     * @param currentPerson current user
     */
    public RemoveUtils(Ui ui, Person currentPerson) {
        this.modulesList = currentPerson.getModulesList();
        this.calculatorUtils = new CalculatorUtils(currentPerson);
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
        this.currentPerson = currentPerson;
        this.ui = ui;
    }

    /**
     * Removes module from user's module list.
     * PartialModule must exist in user's module list.
     *
     * @param moduleCode module to remove.
     */
    public void removeModuleFromUserModuleList(String moduleCode) {
        Scanner in = ui.getScanner();
        final int totalNumberOfModules = modulesList.size();

        try {
            int indexToRemove = getEntryToBeEdited(in, moduleCode, currentPerson, FROM_REMOVE);

            ArrayList<Integer> moduleIndexList = modulesAddedMap.get(moduleCode);
            PartialModule module = modulesList.get(moduleIndexList.get(indexToRemove));

            calculatorUtils.updateCap(FROM_REMOVE, module);
            depopulate(module);

            assert modulesList.size() == totalNumberOfModules - 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes module from both arraylist and hashmap of the user.
     *
     * @param module module object to remove
     */
    private void depopulate(PartialModule module) {
        modulesList.remove(module);
        updateHashmap();
    }

    /**
     * Updates hashmap with the new module list.
     */
    private void updateHashmap() {
        HashMap<String, ArrayList<Integer>> newModuleAddedMap = new HashMap<>();
        for (int i = 0; i < modulesList.size(); i++) {
            String currentModuleCode = modulesList.get(i).getModuleCode();
            if (newModuleAddedMap.containsKey(currentModuleCode)) {
                newModuleAddedMap.get(currentModuleCode).add(i);
            } else {
                ArrayList<Integer> newIndexArray = new ArrayList<>();
                newIndexArray.add(i);
                newModuleAddedMap.put(modulesList.get(i).getModuleCode(), newIndexArray);
            }
        }
        currentPerson.setModulesAddedMap(newModuleAddedMap);
    }
}
