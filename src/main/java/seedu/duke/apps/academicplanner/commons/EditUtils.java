package seedu.duke.apps.academicplanner.commons;

import seedu.duke.apps.ModuleInitializer;
import seedu.duke.apps.capcalculator.commons.CalculatorUtils;
import seedu.duke.exceptions.AcademicException;
import seedu.duke.objects.PartialModule;
import seedu.duke.objects.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EditUtils {
    private final ArrayList<PartialModule> modulesList;
    private final HashMap<String, PartialModule> modulesAddedMap;
    private final ModuleValidator modChecker;
    private final CalculatorUtils calculatorUtils;

    public EditUtils(ModuleInitializer allModules, Person currentPerson) {
        this.modulesList = currentPerson.getModulesList();
        this.modulesAddedMap = currentPerson.getModulesAddedMap();
        this.modChecker = new ModuleValidator(allModules, currentPerson);
        this.calculatorUtils = new CalculatorUtils(currentPerson);
    }

    private static final int FROM_EDIT = 2;
    private static final String PROMPT_NEW_SEMESTER_VALUE = "Enter the new semester value: ";
    private static final String PROMPT_NEW_GRADE = "Enter the new grade: ";
    private static final String ERROR_INVALID_SEMESTER_INDEX = "INVALID SEMESTER INDEX";
    private static final String ERROR_INVALID_GRADE = "INVALID GRADE VALUE";

    /**
     * Changes the current grade of module to the input of the user.
     * PartialModule must exist in user's list and hashmap
     *
     * @param in scanner
     * @param moduleCode code of module to edit the grade
     * @throws AcademicException invalid grade
     */
    public void editModuleGrade(Scanner in, String moduleCode) throws AcademicException {
        System.out.println(PROMPT_NEW_GRADE);
        String gradeValue = in.nextLine().toUpperCase();

        if (!modChecker.isValidGrade(gradeValue)) {
            throw new AcademicException(ERROR_INVALID_GRADE);
        }

        System.out.println(moduleCode);
        updateModuleGrade(moduleCode, gradeValue);
        System.out.println("Grade for " + moduleCode + " successfully updated!");
    }

    /**
     * Updates user's module with new grade and updates user's Cap.
     *
     * @param moduleCode module to edit
     * @param gradeValue grade to edit to
     */
    public void updateModuleGrade(String moduleCode, String gradeValue) {
        for (PartialModule item : modulesList) {
            if (item.getModuleCode().equals(moduleCode)) {
                double oldCap = item.getCap();
                item.setGrade(gradeValue);
                double newCap = item.getCap();
                calculatorUtils.updateCap(FROM_EDIT, item, oldCap, newCap);
                break;
            }
        }
        modulesAddedMap.get(moduleCode).setGrade(gradeValue);
    }

    /**
     * Edits module semester taken when module is in user's list.
     * PartialModule must exist in user's module list and hashmap.
     *
     * @param in scanner
     * @param moduleCode module to edit
     * @throws AcademicException invalid semester index
     */
    public void editModuleSemester(Scanner in, String moduleCode) throws AcademicException {
        System.out.println(PROMPT_NEW_SEMESTER_VALUE);
        String newValue = in.nextLine();

        if (!modChecker.isValidSemester(Integer.parseInt(newValue))) {
            throw new AcademicException(ERROR_INVALID_SEMESTER_INDEX);
        }

        updateModuleSemester(moduleCode, newValue);
        modulesAddedMap.get(moduleCode).setSemesterIndex(Integer.parseInt(newValue));
        System.out.println("Semester for " + moduleCode + " successfully updated!");
    }

    /**
     * Finds the module and updates the semester taken.
     *
     * @param moduleCode module to edit
     * @param newValue new semester index
     */
    public void updateModuleSemester(String moduleCode, String newValue) {
        for (PartialModule item : modulesList) {
            if (item.getModuleCode().equals(moduleCode)) {
                item.setSemesterIndex(Integer.parseInt(newValue));
                return;
            }
        }
    }
}
