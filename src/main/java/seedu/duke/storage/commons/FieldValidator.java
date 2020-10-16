package seedu.duke.storage.commons;

import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import seedu.duke.storage.exceptions.CorruptedSaveFileException;

import static java.lang.Integer.parseInt;

public class FieldValidator {
    private static final int SEMESTER_VALUE_POSITION = 0;
    private static final int MODULE_CODE_POSITION = 1;
    private static final int MODULE_GRADE_POSITION = 2;
    private static final int MODULE_CREDIT_POSITION = 3;

    private static final int MODULE_LOADED = 0;
    private static final int MODULE_LOADING_FAILED = 1;
    private static final int INVALID_SEMESTER = 2;
    private static final int INVALID_MODULE_CODE = 3;
    private static final int INVALID_GRADE = 4;
    private static final int INVALID_MC = 5;

    private ModuleValidator moduleValidator;
    private boolean isAllFieldValid;
    private int[] details;
    private String[] lineItems;

    /**
     * Default constructor for Field Validator.
     *
     * @param details An Array of int storing all the details of loading save file until current entry
     * @param lineItems Different fields of current entry from save file
     * @param moduleValidator Module validator to check validity of modules from save file
     */

    public FieldValidator(int[] details, String[] lineItems, ModuleValidator moduleValidator) {
        this.details = details;
        this.lineItems = lineItems;
        this.isAllFieldValid = true;
        this.moduleValidator = moduleValidator;
    }

    /**
     * Validating all input fields from save file. Note that validator will terminate once
     * an entry fails one check, with priority:
     * Semester > Module code > Grade > Module Credit
     *
     * @return A boolean for whether current entry is valid
     */
    public boolean validate() {
        try {
            int currentSem = parseInt(lineItems[SEMESTER_VALUE_POSITION]);

            if (currentSem > 10 && currentSem < 1) {
                isAllFieldValid = false;
                details[INVALID_SEMESTER]++;
            }
        } catch (NumberFormatException e) {
            isAllFieldValid = false;
            details[INVALID_SEMESTER]++;
        }

        if (isAllFieldValid && !moduleValidator.isModOfferedByNus(lineItems[MODULE_CODE_POSITION])) {
            isAllFieldValid = false;
            details[INVALID_MODULE_CODE]++;
        }

        if (isAllFieldValid && !moduleValidator.isValidGrade(lineItems[MODULE_GRADE_POSITION])) {
            isAllFieldValid = false;
            details[INVALID_GRADE]++;
        }

        if (isAllFieldValid) {
            try {
                parseInt(lineItems[MODULE_CREDIT_POSITION]);
            } catch (NumberFormatException e) {
                isAllFieldValid = false;
                details[INVALID_MC]++;
            }
        }

        if (isAllFieldValid) {
            details[MODULE_LOADED]++;
        } else {
            details[MODULE_LOADING_FAILED]++;
        }

        return isAllFieldValid;
    }

    /**
     * Print full loading details after loading finishes.
     *
     * @param isAllEntryValid boolean stating whether all entries in save file is valid
     * @param modulesLoaded number of modules loaded successfully
     * @throws CorruptedSaveFileException throws an exception if save file is corrupted
     */
    public static void printLoadDetails(boolean isAllEntryValid, int modulesLoaded) throws CorruptedSaveFileException {
        if (isAllEntryValid) {
            if (modulesLoaded == 0) {
                System.out.println("Save file is empty. No module loaded.");
            } else if (modulesLoaded == 1) {
                System.out.println(modulesLoaded + " module loaded.");
            } else {
                System.out.println(modulesLoaded + " modules loaded.");
            }
        } else {
            throw new CorruptedSaveFileException();
        }
    }
}
