package seedu.duke.apps.academicplanner.commands;

import seedu.duke.apps.academicplanner.commons.AddUtils;
import seedu.duke.apps.academicplanner.commons.ModuleValidator;
import static seedu.duke.apps.academicplanner.commons.SharedUtils.getAllOccurrencesOfModule;
import static seedu.duke.apps.academicplanner.commons.SharedUtils.printAllOccurrencesOfModule;
import seedu.duke.apps.academicplanner.exceptions.AcademicException;
import seedu.duke.apps.moduleloader.ModuleLoader;
import seedu.duke.global.Command;
import seedu.duke.global.LoggingTool;
import seedu.duke.global.objects.PartialModule;
import seedu.duke.global.objects.Person;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author jerroldlam
/**
 * Class representing an add module command from the academic planner.
 */
public class AddModuleCommand extends Command {
    public static final String ERROR_INVALID_INTEGER = "INVALID INTEGER";
    private static final String ERROR_INVALID_SEMESTER_INDEX = "INVALID SEMESTER INDEX";
    private static final String ERROR_INVALID_GRADE = "INVALID GRADE VALUE";
    private static final String ERROR_NOT_OFFERED = " IS NOT OFFERED BY NUS";
    private static final String ERROR_DUPLICATE_MOD
            = "You already have this mod on your calendar and you cannot retake!";
    private static final String VALID_GRADES = "Valid grades are:\n"
            + "\tLetter Grades: A+, A, A-, B+, B, B-, C+, C, D+, D, F\n"
            + "\tSpecial Grades: CS, CU, S, U, W, IC, IP, AUD, WU, EXE\n"
            + "\tIf you have yet to have a grade for the module: NT";
    private static final String VALID_SEMESTERS = "\tValid semesters are integers from 1 to 10, inclusive";
    private static final String RETAKE_MOD = "This is a module that you are retaking!";
    private static final String WARNING = "Note that you cannot retake this module in any of the previous semester.\n";
    private static final String LOG_FILE_NAME = "AddModuleCommand.log";
    private static final String LOGGER_NAME = "AddModuleCommand";
    private static final String INVALID_RETAKE_SEMESTER
            = "Cannot retake this module in any of the semester listed above!";

    private static Logger logger;
    private static FileHandler fh;
    private AddUtils addUtils;
    private ModuleValidator moduleValidator;
    private Person currentPerson;
    private Ui ui;
    private Scanner in;
    private String moduleCode;
    private Storage storage;

    /**
     * Default constructor for Add Module command.
     *
     * @param allModules all modules offered by NUS
     * @param currentPerson current user
     * @param ui Ui
     * @param moduleCode module code
     */
    public AddModuleCommand(ModuleLoader allModules, Person currentPerson, Ui ui, String moduleCode, Storage storage) {
        this.addUtils = new AddUtils(allModules, currentPerson);
        this.moduleValidator = new ModuleValidator(allModules, currentPerson);
        this.ui = ui;
        this.in = ui.getScanner();
        this.moduleCode = moduleCode;
        this.currentPerson = currentPerson;
        this.storage = storage;
    }

    /**
     * Adds a module to the user's academic calendar if it exists in FullModule,
     * else does not add module into user's academic calendar.
     * Validates user's input semester and grade.
     * If either is invalid, does not add module into user's academic calendar.
     * Updates user's statistics as well.
     */
    @Override
    public void execute() throws AcademicException, IOException {
        initialiseLogger();
        logger.log(Level.INFO,"Executing add command.");

        boolean isRetake = validateModuleCode();

        int semesterValue = getSemesterValue(isRetake);
        String gradeValue = getGradeValue();
        int moduleCredit = addUtils.getModuleCreditForModule(moduleCode);

        assertInputs(semesterValue, moduleCredit);
        addUtils.addModuleToUser(moduleCode, semesterValue, gradeValue, moduleCredit);

        logger.log(Level.INFO,"Finished executing add command.");
        fh.close();

        storage.saver(currentPerson);
    }

    /**
     * Prompts and accepts user input for grade value.
     * Also checks for grade value validity.
     *
     * @return Valid Grade value
     * @throws AcademicException when invalid grade value is given
     */
    private String getGradeValue() throws AcademicException {
        promptUserToEnterGrade();
        String gradeValue = in.nextLine().trim().toUpperCase();
        validateInputGrade(gradeValue);
        return gradeValue;
    }

    /**
     * Prompts and accepts user input for semester value.
     * Also checks for semester value validity.
     *
     * @return valid semester value
     * @throws AcademicException when invalid semester value is given
     */
    private int getSemesterValue(boolean isRetake)
            throws AcademicException {
        promptUserToEnterSemester();
        String userInput = in.nextLine().trim();
        int semesterValue = validateInputSemester(userInput, isRetake);
        return semesterValue;
    }

    /**
     * Initialises logger for use.
     *
     * @throws IOException when logger fails to initialise
     */
    private void initialiseLogger() throws IOException {
        fh = new FileHandler(LOG_FILE_NAME);
        logger = new LoggingTool(LOGGER_NAME,fh).initialize();
    }

    /**
     * Validates critical inputs via assertions.
     *
     * @param semesterValue input semester value
     * @param moduleCredit module credit retrieved from NUSMODS API
     */
    private void assertInputs(int semesterValue, int moduleCredit) {
        assert semesterValue > 0;
        assert moduleCredit >= 0;
    }

    /**
     * Throws AcademicException if the module code is not offered by NUS,
     * or module is already taken by user.
     *
     * @throws AcademicException thrown when invalid module code is requested to be added
     */
    private boolean validateModuleCode() throws AcademicException {
        boolean isRetake = false;
        if (!moduleValidator.isModOfferedByNus(moduleCode)) {
            logger.log(Level.WARNING,"Module entered not offered by NUS.");
            fh.close();
            throw new AcademicException(moduleCode + ERROR_NOT_OFFERED);
        }

        if (moduleValidator.isModTakenByUser(moduleCode)) {
            PartialModule module = getPartialModule();

            if (moduleValidator.isRetakeGrade(module.getGrade())) {
                isRetake = true;
                ArrayList<PartialModule> allOccurrencesOfModule
                        = getAllOccurrencesOfModule(currentPerson, moduleCode);

                System.out.println(RETAKE_MOD);
                printAllOccurrencesOfModule(allOccurrencesOfModule);
                System.out.println(WARNING);
            } else {
                logger.log(Level.WARNING, "Module entered is duplicated.");
                fh.close();
                throw new AcademicException(ERROR_DUPLICATE_MOD);
            }
        }

        return isRetake;
    }

    /**
     * Returns partial module object of module code.
     *
     * @return PartialModule
     */
    private PartialModule getPartialModule() {
        HashMap<String, ArrayList<Integer>> modulesAddedMap = currentPerson.getModulesAddedMap();
        ArrayList<PartialModule> modulesAddedList = currentPerson.getModulesList();

        ArrayList<Integer> moduleIndexList = modulesAddedMap.get(moduleCode);

        int highestCapIndex = moduleIndexList.get(0);
        double highestCap = modulesAddedList.get(highestCapIndex).getCap();

        for (int i = 0; i < moduleIndexList.size(); i++) {
            double currentCap = modulesAddedList.get(moduleIndexList.get(i)).getCap();
            if (currentCap > highestCap) {
                highestCapIndex = moduleIndexList.get(i);
                highestCap = currentCap;
            }
        }

        PartialModule module = modulesAddedList.get(highestCapIndex);
        return module;
    }

    /**
     * Validates user inputs and returns semester value if inputs are valid,
     * else throws Academic Exception.
     *
     * @param userInput semester value
     * @return semesterIndex
     * @throws AcademicException thrown when any input is invalid
     */
    private int validateInputSemester(String userInput, boolean isRetake)
            throws AcademicException {
        int semesterValue;
        try {
            semesterValue = Integer.parseInt(userInput);
        } catch (Exception e) {
            logger.log(Level.WARNING,"Semester entered is not an integer.");
            fh.close();
            throw new AcademicException(ERROR_INVALID_INTEGER);
        }

        if (!ModuleValidator.isValidSemester(semesterValue)) {
            logger.log(Level.WARNING,"Semester entered is invalid.");
            fh.close();
            throw new AcademicException(ERROR_INVALID_SEMESTER_INDEX);
        }

        if (isRetake) {
            ArrayList<PartialModule> modulesAddedList = currentPerson.getModulesList();

            for (int i = 0; i < modulesAddedList.size(); i++) {
                int currentSemester = modulesAddedList.get(i).getSemesterIndex();
                String currentModule = modulesAddedList.get(i).getModuleCode();

                if (currentSemester == semesterValue && currentModule.contains(moduleCode)) {
                    throw new AcademicException(INVALID_RETAKE_SEMESTER);
                }
            }
        }
        return semesterValue;
    }

    /**
     * Prints prompt and help messages for user to enter module's grade.
     */
    private void promptUserToEnterGrade() {
        System.out.println("Grade received for " + moduleCode.toUpperCase() + "?");
        System.out.println(VALID_GRADES);
    }

    /**
     * Prints prompt and help messages for user to enter module's semester taken.
     */
    private void promptUserToEnterSemester() {
        System.out.println("Semester you plan to take " + moduleCode.toUpperCase() + "?");
        System.out.println(VALID_SEMESTERS);
    }

    /**
     * Throws exception if invalid grade is entered.
     *
     * @param gradeValue user input grade
     * @throws AcademicException thrown when invalid grade is entered
     */
    private void validateInputGrade(String gradeValue) throws AcademicException {
        if (!moduleValidator.isValidGrade(gradeValue)) {
            logger.log(Level.WARNING,"Grade entered is invalid.");
            fh.close();
            throw new AcademicException(ERROR_INVALID_GRADE);
        }
    }
}
